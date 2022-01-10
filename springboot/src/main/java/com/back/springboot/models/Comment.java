package com.back.springboot.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Comment {

      
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Publication publication;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> lCommentLike;

    @ManyToOne
    private User user;

    private String contenu; 

    private Date dateCreated;



    

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

    public List<CommentLike> getlCommentLike() {
        return lCommentLike;
    }

    public void setlCommentLike(List<CommentLike> lCommentLike) {
        this.lCommentLike = lCommentLike;
    }

    
   
    

}
