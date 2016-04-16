package cs544.lab.ea_blogs.service;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.lab.ea_blogs.dao.IUserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BlogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogsController.class);
	

	@Resource
	private IUserDao userDao;
	
	
	@RequestMapping("/")
	public String redirectRoot() {
		return "redirect:/articles";
	}

	@Transactional
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String userList(Model model) {
		logger.info("Get all users:");

//		model.addAttribute("users", userDao.findAll(new Sort(Direction.ASC, "name")));
		
		return "userList";
	}

	@Transactional
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public String article(Model model) {
		logger.info("Get all users:");

//		model.addAttribute("users", userDao.findAll(new Sort(Direction.ASC, "name")));
		
		return "userList";
	}
}
