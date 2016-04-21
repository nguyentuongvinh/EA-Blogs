package cs544.lab.ea_blogs.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4159563455063691436L;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@NotBlank(message="may not be EMPTY or BLANK!")
	private String username;
	
	@Column(nullable = false)
	@Size(min=6, max=16)
	private String password;
	
	@NotBlank(message="may not be EMPTY or BLANK!")
	private String fullname;
	
	@NotBlank(message="may not be EMPTY or BLANK!")
	@Email
	private String email;
	
	@Column(name = "aboutuser")
	@NotBlank(message="may not be EMPTY or BLANK!")
	private String aboutUser;
	
	@Lob
	private byte[] photo;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<UserRole> roleSet = new HashSet<UserRole>();
	
	public User() {
	}

	public User(String username, String password, String fullname, String email, String aboutUser) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.aboutUser = aboutUser;
	}


	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}


	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public Set<UserRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<UserRole> roleSet) {
		this.roleSet = roleSet;
	}
	
}
