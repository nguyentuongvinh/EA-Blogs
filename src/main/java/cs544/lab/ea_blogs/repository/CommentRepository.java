package cs544.lab.ea_blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.lab.ea_blogs.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	public List<Comment> findCommentsByArticleId(Integer articleId);
}
