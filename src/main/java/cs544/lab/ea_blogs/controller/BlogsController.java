package cs544.lab.ea_blogs.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cs544.lab.ea_blogs.model.Category;
import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.repository.UserRepository;
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
		map.put("articles", articleService.findFiveLatestArticle());

		return "main";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String userList(Model model) {
		logger.info("Get all users:");

		List<User> users = userService.findByUsername("reader");
		for (User user : users)
			logger.info(user.getPassword());
		
		return "main";
	}

	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public String article(Model model) {
		return "articles";
	}
}
