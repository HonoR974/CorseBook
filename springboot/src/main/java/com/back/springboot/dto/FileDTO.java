package com.back.springboot.dto;

import lombok.Data;

@Data
public class FileDTO {

    
    private String url;

    private String name;

    private String type;

public FileDTO()
{
    
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

 

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FileDTO [name=" + name + ", type=" + type + ", url=" + url + "]";
    }

    
    
}
