package com.back.springboot.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

      
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Publication publication;

    @ManyToOne
    private User user;

    private String contenu; 

    private Date dateCreated;

    @Column(name="user")
    @ManyToMany
    private List<User> likeUser;
    

    public Comment(){}

    public long getId() {
        return id;
    }

  
    public void setId(long id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublications(Publication publicationRequest) {
        this.publication = publicationRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }


   
    

}
