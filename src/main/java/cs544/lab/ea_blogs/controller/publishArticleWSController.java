package cs544.lab.ea_blogs.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cs544.lab.ea_blogs.model.Article;
import cs544.lab.ea_blogs.service.ArticleService;
import cs544.lab.ea_blogs.service.CategoryService;
import cs544.lab.ea_blogs.service.UserService;

@RestController
public class publishArticleWSController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/article/PublishArticle/", method = RequestMethod.POST)
	public void processPublishArticle(HttpServletRequest request, Model model, @RequestBody String jsonArticle) {
		JSONObject jsonObj = new JSONObject(jsonArticle);
		
		Article article = new Article();
		article.setSubject(jsonObj.getString("subject"));
		article.setContent(jsonObj.getString("content"));
		article.setSubtilte(jsonObj.getString("subtitle"));
		article.setPublishDate(new Date());
		article.setCategory(categoryService.findById(jsonObj.getInt("category_id")));
		article.setPublishedBy(userService.findByUsername(jsonObj.getString("username")));
		
		articleService.saveOrUpdate(article);
    }
  
}
