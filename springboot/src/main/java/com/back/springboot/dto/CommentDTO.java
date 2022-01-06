package com.back.springboot.dto;


import lombok.Data;

@Data
public class CommentDTO {

    private long id;

    private long id_publication; 
  
    private String  username;

    private String contenu; 

    public CommentDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId_publication() {
        return id_publication;
    }

    public void setId_publication(long id_publication) {
        this.id_publication = id_publication;
    }


    
    
}
