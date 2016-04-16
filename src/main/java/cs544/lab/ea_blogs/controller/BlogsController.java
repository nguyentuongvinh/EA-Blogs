package cs544.lab.ea_blogs.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.repository.UserRepository;
import cs544.lab.ea_blogs.service.ArticleService;
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

	@Resource
	private UserRepository userDao;
	
	
	@RequestMapping("/")
	public String redirectRoot() {
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
		logger.info("Get all users:");

		return "articles";
	}
}
