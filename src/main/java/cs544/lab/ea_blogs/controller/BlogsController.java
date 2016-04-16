package cs544.lab.ea_blogs.controller;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.lab.ea_blogs.model.User;
import cs544.lab.ea_blogs.repository.UserRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BlogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogsController.class);
	

	@Resource
	private UserRepository userDao;
	
	
	@RequestMapping("/")
	public String redirectRoot() {
		return "redirect:/articles";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String userList(Model model) {
		logger.info("Get all users:");

		List<User> users = userDao.findByUsername("reader");
		for (User user : users)
			logger.info(user.getUsername());
		
		return "main";
	}

	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public String article(Model model) {
		logger.info("Get all users:");

		return "articles";
	}
}
