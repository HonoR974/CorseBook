package com.back.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class File {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String url;

    private String name;

    @ManyToOne
    private Publication publication;

    @OneToOne(mappedBy = "profilePicture")
    private User user;

    @ManyToOne
    private Event event;


    public File()
    {}

    public File(String urlSend, String nameSend)
    {
        this.url = urlSend;
        this.name = nameSend;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


    
    
}
