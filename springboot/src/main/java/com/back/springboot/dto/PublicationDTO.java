package com.back.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class PublicationDTO {
    
    private long id;

    private long countLike;

    private String contenu;

    private String username;

    private List<FileDTO> listFile;
    
    public PublicationDTO() {
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

    
  
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCountLike() {
        return countLike;
    }

    public void setCountLike(long countLike) {
        this.countLike = countLike;
    }

    public List<FileDTO> getListFile() {
        return listFile;
    }

    public void setListFile(List<FileDTO> listFile) {
        this.listFile = listFile;
    }

    @Override
    public String toString() {
        return "PublicationDTO [contenu=" + contenu + ", countLike=" + countLike + ", id=" + id + ", listFile="
                + listFile + ", username=" + username + "]";
    }




    
}
