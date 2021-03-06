package cs544.lab.ea_blogs.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cs544.lab.ea_blogs.model.Article;
import cs544.lab.ea_blogs.model.Comment;
import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.model.UserRole;
import cs544.lab.ea_blogs.service.ArticleService;
import cs544.lab.ea_blogs.service.CategoryService;
import cs544.lab.ea_blogs.service.CommentService;
import cs544.lab.ea_blogs.service.UserService;


/**
 * Handles requests for the application pages.
 */
@Controller
@Transactional
@SessionAttributes(value={"loginRequestPage", "logoutRequestPage", "categories"})
public class BlogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogsController.class);

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping("/")
	public String redirectRoot(Map<String, Object> map, HttpServletRequest request) {
		map.put("categories", categoryService.findAll());
		map.put("articles", articleService.findFiveLatestArticle());

		return "main";
	}
	
	@RequestMapping(value = "/article/category/{categoryId}/", method = RequestMethod.GET)
	public String articleByCategoryId(@PathVariable("categoryId") Integer categoryId, Map<String, Object> map) {
		map.put("categories", categoryService.findAll());
		map.put("articles", articleService.findArticleByCategory(categoryId));

		return "articleByCategoryId";
	}
	
	@RequestMapping(value = "/article/{articleId}/", method = RequestMethod.GET)
	public String articleDetail(@PathVariable("articleId") Integer articleId, Map<String, Object> map) {
		map.put("categories", categoryService.findAll());
		map.put("article", articleService.findArticleById(articleId));
		List<Comment> comment = commentService.findCommentsByArticleId(articleId);
		map.put("comments", comment);
		map.put("NumOfComments", comment.size());

		return "articleDetail";
	}
	
	/**
	 * @param articleId
	 * @param comment
	 * @param map
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/article/{articleId}/postComment/", method = RequestMethod.POST)
	public String articlePostComment(@PathVariable("articleId") Integer articleId,
			@Valid @ModelAttribute("comment") String comment, Map<String, Object> map, 
			Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUsername(username);
		Article article = articleService.findArticleById(articleId);
		
		Comment cmt = new Comment(comment, user, article);
		commentService.saveComment(cmt);
		
		return "redirect:/article/{articleId}/";
	}
	
	@RequestMapping(value="/articleImage/{articleId}", method=RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getArticleImage(@PathVariable int articleId){
		return articleService.findArticleById(articleId).getImage();
	}

	@RequestMapping(value="/userPhoto/{userId}", method=RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getUserPhoto(@PathVariable int userId){
		byte[] image;
		if (userService.findByUserId(userId).getPhoto() != null)
			image = userService.findByUserId(userId).getPhoto();
		else {
			File fi = new File("img/defaultUserPhoto.jpg");
			try {
				image =  Files.readAllBytes(fi.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				image = userService.findByUserId(userId).getPhoto();
			}
		}
		
		return image;
	}
	
	@RequestMapping("/loginRequest")
	public String showLoginView(HttpServletRequest request, Model model, 
			@RequestParam(name="regFlag", required=false, defaultValue="false") Boolean regFlag) {

		logger.info("Login request from: {}", request.getHeader("referer"));
		model.addAttribute("loginRequestPage", request.getHeader("referer"));
		model.addAttribute("error", false);
		model.addAttribute("regFlag", regFlag);
		model.addAttribute("categories", categoryService.findAll());
		return "loginView";
	}
	
	@RequestMapping("/loginFailed")
	public String showLoginFailed(Model model) {

		model.addAttribute("error", true);
		model.addAttribute("categories", categoryService.findAll());
		return "loginView";
	}
	
	@RequestMapping("/loginSuccessTarget")
	public String showLoginSuccessTarget(HttpServletRequest request, Model model) {

		if (! model.containsAttribute("loginRequestPage")){
			model.addAttribute("loginRequestPage", request.getContextPath());
		}
		model.addAttribute("categories", categoryService.findAll());
		return "loginSuccess";
	}
	
	@RequestMapping("/logoutSuccessTarget")
	public String showLogoutSuccessTarget(HttpServletRequest request, Model model) {

		logger.info("Logout request from: {}", request.getHeader("referer"));
		model.addAttribute("logoutRequestPage", request.getHeader("referer"));
		model.addAttribute("categories", categoryService.findAll());
		return "logoutSuccess";
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
	@RequestMapping(value = "/user/{publishedBy}/", method = RequestMethod.GET)
	public String articlesByUserId(@PathVariable("publishedBy") Integer publishedBy, Map<String, Object> map) {
		map.put("categories", categoryService.findAll());
		map.put("articles", articleService.findByPublishedBy(publishedBy));
		map.put("userEntity", userService.findByUserId(publishedBy));
		
		return "articleByUserId";
	}
	
	@RequestMapping("/newArticle")
	public String newArticle(@RequestParam("username") String username, Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("userEntity", userService.findByUsername(username));
		return "articleNew";
	}
	
	@RequestMapping(value = "/publishArticle", method = RequestMethod.POST)
	public String postComment(@RequestParam("username") String username, @RequestParam("subject") String subject, 
			@RequestParam("subtitle") String subtitle, @RequestParam("categoryId") Integer categoryId,
			@RequestParam("content") String content, @RequestParam(name="picFile", required=false) MultipartFile picFile) {
		
		byte[] picBytes = null;
		if (picFile != null){
			ByteArrayOutputStream baOutput = new ByteArrayOutputStream();
		    byte[] buffer = new byte[4096];
		    int n;
		    try {
		    	InputStream inputStream = picFile.getInputStream();
				while ((n=inputStream.read(buffer))>0) {
					baOutput.write(buffer, 0, n);
				}
				picBytes = baOutput.toByteArray();
			}
		    catch (IOException e) {
				logger.error("Upload pic file ERROR: filename: {}", picFile.getOriginalFilename());
			}
		}
		
	    Article article = new Article(userService.findByUsername(username), subject, subtitle, 
	    		categoryService.findById(categoryId), content, picBytes);

	    articleService.saveOrUpdate(article);
	    int articleId = article.getId();
		logger.info("Publish article by: {}, then redirect to articleId: {}", username, articleId);
		return "redirect:/article/" + articleId + "/";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegisterView(@ModelAttribute("user") User user, Model model) {

		model.addAttribute("categories", categoryService.findAll());
		return "registerView";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String showRegisterSuccessView(@Valid User user, BindingResult br, Model model) {

		model.addAttribute("categories", categoryService.findAll());
		String view = "redirect:/loginRequest";
		if (br.hasErrors())
			view = "registerView";
		else{
			user.getRoleSet().add(UserRole.ROLE_READER);
			user.getRoleSet().add(UserRole.ROLE_PUBLISHER);
			userService.saveOrUpdate(user);
			model.addAttribute("regFlag", true);
			logger.info("Register success, userID: {}", user.getId());
		}

		return view;
	}
	
}
