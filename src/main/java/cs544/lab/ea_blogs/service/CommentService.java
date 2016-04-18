package cs544.lab.ea_blogs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.lab.ea_blogs.model.Comment;
import cs544.lab.ea_blogs.repository.CommentRepository;

@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> findCommentsByArticleId(Integer articleId) {
		return commentRepository.findCommentsByArticleId(articleId);
	}
	
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}
}
