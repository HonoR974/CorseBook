package com.back.springboot.dto;

import lombok.Data;

@Data
public class FileDTO {

    
    private String url;

    private String name;


    public FileDTO()
    {
        
    }
    public FileDTO(String url_champs, String nom)
    {
        this.url = url_champs;
        this.name = nom;
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
        return "FileDTO [name=" + name + ", url=" + url + "]";
    }

    
    
}
