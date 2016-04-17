package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cs544.lab.ea_blogs.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	public List<Article> findArticleByCategoryId(Integer categoryId);
	
	public List<Article> findAll();
	
	public List<Article> findArticlesPostedByPublishedBy(Integer publishedBy);
	
	@Query("SELECT a FROM Article a")
	public List<Article> findFiveLatestArticle();
}
