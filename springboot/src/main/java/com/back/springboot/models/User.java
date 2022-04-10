package com.back.springboot.models;


import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	private String matchingPassword;

	// 	publication 
	@OneToMany(mappedBy = "user")
    private List<Publication> publication;

	@ManyToMany(mappedBy = "likeUser")
	private List<Publication> publicationsLiked;


	//	comment 
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@ManyToMany(mappedBy = "likeUser")
	private List<Comment> commentsLiked;

	//	contact 
	@ManyToMany
	private List<User> listContact;

	@ManyToMany
	private List<User> listInvitation;


	//	file
	@OneToOne
	@JoinColumn(name = "file_id", referencedColumnName = "id")
	private File profilePicture;

	//	chat
	@ManyToMany(mappedBy = "users")
	private List<Chat> chats;

	// event 
	@ManyToMany
	private List<Event> listEvent;



	
	public User() {
	}


	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getListInvitation() {
		return listInvitation;
	}

	public void setListInvitation(List<User> listInvitation) {
		this.listInvitation = listInvitation;
	}


	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public List<Publication> getPublication() {
		return publication;
	}

	public void setPublication(List<Publication> publication) {
		this.publication = publication;
	}

	public List<Publication> getPublicationsLiked() {
		return publicationsLiked;
	}


	public void setPublicationsLiked(List<Publication> publicationsLiked) {
		this.publicationsLiked = publicationsLiked;
	}


	public List<User> getListContact() {
		return listContact;
	}

	public void setListContact(List<User> listContact) {
		this.listContact = listContact;
	}

	public List<Comment> getCommentsLiked() {
		return commentsLiked;
	}


	public void setCommentsLiked(List<Comment> commentsLiked) {
		this.commentsLiked = commentsLiked;
	}


	public File getProfilePicture() {
		return profilePicture;
	}


	public void setProfilePicture(File profilePicture) {
		this.profilePicture = profilePicture;
	}


	public List<Chat> getChats() {
		return chats;
	}


	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}


	public List<Event> getListEvent() {
		return listEvent;
	}


	public void setListEvent(List<Event> listEvent) {
		this.listEvent = listEvent;
	}

	
}