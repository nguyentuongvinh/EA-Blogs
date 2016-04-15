package cs544.lab.ea_blogs.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	@Range(min=1 , max=10)
	private float rating;

	
	public Comment() {
		super();
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		user.addComment(this);
	}




	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getCommentId() {
		return commentId;
	}


}
