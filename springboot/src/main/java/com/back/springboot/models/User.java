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

	@OneToMany(mappedBy = "user")
    private List<Publication> publication;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@OneToMany(mappedBy = "user")
	private List<PubLike> lPubLikes;

	@OneToMany(mappedBy = "user")
	private List<CommentLike> lCommentLikes;

	@ManyToMany
	private List<User> listContact;

	
	@ManyToMany
	private List<User> lInvitationContact;

	public User() {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<PubLike> getlPubLikes() {
		return lPubLikes;
	}

	public void setlPubLikes(List<PubLike> lPubLikes) {
		this.lPubLikes = lPubLikes;
	}

	public List<CommentLike> getlCommentLikes() {
		return lCommentLikes;
	}

	public void setlCommentLikes(List<CommentLike> lCommentLikes) {
		this.lCommentLikes = lCommentLikes;
	}

	public List<User> getListContact() {
		return listContact;
	}

	public void setListContact(List<User> listContact) {
		this.listContact = listContact;
	}

	public List<User> getlInvitationContact() {
		return lInvitationContact;
	}

	public void setlInvitationContact(List<User> lInvitationContact) {
		this.lInvitationContact = lInvitationContact;
	}



	
	

}