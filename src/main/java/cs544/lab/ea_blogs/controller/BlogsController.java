package cs544.lab.ea_blogs.controller;


import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs544.lab.ea_blogs.model.Article;
import cs544.lab.ea_blogs.model.Comment;
import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.service.ArticleService;
import cs544.lab.ea_blogs.service.CategoryService;
import cs544.lab.ea_blogs.service.CommentService;
import cs544.lab.ea_blogs.service.UserService;

/**
 * Handles requests for the application pages.
 */
@Controller
@SessionAttributes(value={"loginRequestPage", "categories"})
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
		map.put("articles", articleService.findAll());

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
	
	@Transactional
	@RequestMapping(value="/articleImage/{articleId}", method=RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getArticleImage(@PathVariable int articleId){
		return articleService.findArticleById(articleId).getImage();
	}

	@Transactional
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
	public String showLoginView(HttpServletRequest request, Model model) {

		logger.info("Login request from: {}", request.getHeader("referer"));
		model.addAttribute("loginRequestPage", request.getHeader("referer"));
		model.addAttribute("error", false);
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
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
	@RequestMapping(value = "/user/{userId}/", method = RequestMethod.GET)
	public String articlesByUserId(@PathVariable("userId") Integer userId, Map<String, Object> map) {
		map.put("categories", categoryService.findAll());
		map.put("articles", articleService.findByPostedId(userId));
		return "articleByUserId";
	}
}
