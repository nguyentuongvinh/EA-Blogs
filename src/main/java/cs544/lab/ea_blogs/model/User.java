package cs544.lab.ea_blogs.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<UserRole> roleSet = new HashSet<UserRole>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Comment> commentList = new ArrayList<Comment>();
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}




	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public Set<UserRole> getRoleSet() {
		return roleSet;
	}

	public void addComment(Comment c){
		commentList.add(c);
	}

	public void removeComment(Comment c){
		commentList.remove(c);
	}

}
