package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.Article;



@Repository
@Transactional
@Cacheable
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	public List<Article> findArticleByCategoryId(Integer categoryId);
	
	public List<Article> findArticlesPostedByPublishedBy(Integer publishedBy);
	
	public List<Article> findFirst5ByOrderByPublishDateDesc();
	
	@Modifying
	@Query("UPDATE Article a SET a.image=:image WHERE a.id=:id")
	int updateImageByArticleId(@Param("id") Integer id, @Param("image") byte[] image);
	
}
