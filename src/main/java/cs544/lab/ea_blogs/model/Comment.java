package cs544.lab.ea_blogs.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment", catalog = "ea_blogs")
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1790462242322331230L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date")
	private Date postDate;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private User postedBy;
	
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	
	public Comment() {
	}

	public Comment(String content, User postedBy, Article article) {
		super();
		this.content = content;
		this.postDate = new Date();
		this.postedBy = postedBy;
		this.article = article;
	}

	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}	
	
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public Date getPostDate() {
		return postDate;
	}
	
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
	public User getPostedBy() {
		return postedBy;
	}
	
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
	public Article getArticle() {
		return article;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
}
