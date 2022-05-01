package com.back.springboot.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long countLike;

    private String contenu;

    private Date dateCreate;

    @ManyToOne
    private User user;


    @OneToMany( mappedBy = "publication")
    private List<File> listFile;

    @OneToMany( mappedBy = "publication")
    private List<Comment> listComments;

    @ManyToMany 
    private List<User> likeUser;

    @OneToMany( mappedBy = "publication")
    private List<Marker> listMarkers;


    public Publication() {
    }

    public Publication(String content) {
        this.contenu = content;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long  getCountLike() {
        return countLike;
    }

    public void setCountLike(long like) {
        this.countLike = like;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<File> getListFile() {
        return listFile;
    }

    public void setListFile(List<File> listFile) {
        this.listFile = listFile;
    }

    public List<Comment> getListComments() {
        return listComments;
    }

    public void setListComments(List<Comment> listComments) {
        this.listComments = listComments;
    }

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }

    public List<Marker> getListMarkers() {
        return listMarkers;
    }

    public void setListMarkers(List<Marker> listMarkers) {
        this.listMarkers = listMarkers;
    }




    
}
