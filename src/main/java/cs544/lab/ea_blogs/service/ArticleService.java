package cs544.lab.ea_blogs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.Article;
import cs544.lab.ea_blogs.repository.ArticleRepository;

@Service
@Transactional
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> findArticleByCategory(Integer categoryId) {
		return articleRepository.findArticleByCategoryId(categoryId);
	}
	
	public List<Article> findFiveLatestArticle() {
		return articleRepository.findFiveLatestArticle();
	}
	
	public List<Article> findAll() {
		return articleRepository.findAll();
	}
}
