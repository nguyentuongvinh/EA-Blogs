package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cs544.lab.ea_blogs.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query("SELECT a FROM Article a WHERE a.category.id = :categoryId")
	public List<Article> findArticleByCategory(@Param("categoryId") Integer categoryId);
	
	public List<Article> findAll();
	
	@Query("SELECT a FROM Article a WHERE a.publishedBy.id = :userId")
	public List<Article> findArticlePostedByUserId(@Param("userId") Integer userId);
}
