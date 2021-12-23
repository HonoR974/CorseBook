package com.back.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class File {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String url;

    private String name;


    public File()
    {}

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

    @Override
    public String toString() {
        return "File [id=" + id + ", name=" + name + ", url=" + url + "]";
    }
    
    
    
}
