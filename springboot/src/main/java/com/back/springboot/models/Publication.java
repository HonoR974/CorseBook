package com.back.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long countLike;

    private String contenu;

    @ManyToOne
    private User user;

    @ManyToOne
    private Statut statut;


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

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Publication [contenu=" + contenu + ", countLike=" + countLike + ", id=" + id + ", statut=" + statut
                + ", user=" + user + "]";
    }

    
    
    
    
}
