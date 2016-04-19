package cs544.lab.ea_blogs.controller;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.lab.ea_blogs.model.Article;
import cs544.lab.ea_blogs.model.Comment;
import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.service.ArticleService;
import cs544.lab.ea_blogs.service.CategoryService;
import cs544.lab.ea_blogs.service.CommentService;
import cs544.lab.ea_blogs.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
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
	
	@RequestMapping(value = "/article/{articleId}/postComment/", method = RequestMethod.POST)
	public String articlePostComment(@PathVariable("articleId") Integer articleId,
			@Valid @ModelAttribute("comment") String comment, Map<String, Object> map, 
			Principal principal) {
		Article article = articleService.findArticleById(articleId);
		User user = userService.findByUsername("reader");
		
		Comment cmt = new Comment(comment, user, article);
		commentService.saveComment(cmt);
		
		return "redirect:/article/{articleId}/";
	}
}
