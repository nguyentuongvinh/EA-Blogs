package cs544.lab.ea_blogs.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6373069329047194876L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String subject;
	
	@Column(nullable=false)
	private String subtitle;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String content;
	
	@Lob
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_date")
	private Date publishDate;
	
	@ManyToOne
	@JoinColumn(name="published_by")
	private User publishedBy;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToMany(mappedBy = "article")
	private List<Comment> comments = new ArrayList<Comment>();
		
	public Article() {
	}

	
	public Article(String subject, String subtitle, String content, byte[] image) {
		super();
		this.subject = subject;
		this.subtitle = subtitle;
		this.content = content;
		this.image = image;
		this.publishDate = new Date();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	

	public Date getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public User getPublishedBy() {
		return publishedBy;
	}
	
	public void setPublishedBy(User publishedBy) {
		this.publishedBy = publishedBy;
	}
	

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
