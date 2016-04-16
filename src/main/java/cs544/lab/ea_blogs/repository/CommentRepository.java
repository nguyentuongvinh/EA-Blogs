package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cs544.lab.ea_blogs.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query("SELECT c FROM Comment c WHERE c.article.id = :articleId")
	public List<Comment> findCommentsByArticleId(@Param("articleId") Integer articleId);
}
