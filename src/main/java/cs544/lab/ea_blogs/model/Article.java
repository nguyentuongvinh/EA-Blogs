package cs544.lab.ea_blogs.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "article", catalog = "ea_blogs")
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String subject;
	private String subtilte;
	private String content;
	private Date publishDate;
	private User publishedBy;
	private Category category;
	private Set<Comment> comments = new HashSet<Comment>();
		
	public Article() {
	}

	public Article(String subject, String content, Date publishDate, User publishedBy, Category category) {
		super();
		this.subject = subject;
		this.content = content;
		this.publishDate = publishDate;
		this.publishedBy = publishedBy;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "subject", nullable=false)
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "content", nullable=false)
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", length = 10)
	public Date getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@OneToOne
	@JoinColumn(name="published_by")
	public User getPublishedBy() {
		return publishedBy;
	}
	
	public void setPublishedBy(User publishedBy) {
		this.publishedBy = publishedBy;
	}
	
	@OneToOne
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Column(name = "subtitle")
	public String getSubtilte() {
		return subtilte;
	}

	public void setSubtilte(String subtilte) {
		this.subtilte = subtilte;
	}	
}
