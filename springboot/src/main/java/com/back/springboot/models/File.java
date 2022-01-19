package com.back.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class File {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String url;

    private String name;

    @ManyToOne
    private Publication publication;


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

    @Override
    public String toString() {
        return "File [id=" + id + ", name=" + name + ", publication=" + publication + ", url=" + url + "]";
    }
    
    
    
}
