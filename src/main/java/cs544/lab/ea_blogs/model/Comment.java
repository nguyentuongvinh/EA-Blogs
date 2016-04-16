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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comment", catalog = "ea_blogs")
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Date postDate;
	private User postedBy;
	private Artical artical;
	
	public Comment() {
	}

	public Comment(String content, Date postDate, User postedBy, Artical artical) {
		super();
		this.content = content;
		this.postDate = postDate;
		this.postedBy = postedBy;
		this.artical = artical;
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
	
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}	
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "post_date", length = 10)
	public Date getPostDate() {
		return postDate;
	}
	
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	@OneToOne
	@JoinColumn(name="post_id")
	public User getPostedBy() {
		return postedBy;
	}
	
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artical_id", nullable = false)
	public Artical getArtical() {
		return artical;
	}
	
	public void setArtical(Artical artical) {
		this.artical = artical;
	}
	
}
