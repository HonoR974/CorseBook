package com.back.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class PublicationDTO {
    
    private long id;

    private long countLike;
    
    private boolean liked;

    private String contenu;

    private String username;

    private List<FileDTO> listFile;

    private List<CommentDTO> listComments;
    
    private boolean createdByUser;

    private String statut; 
    
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public List<CommentDTO> getListComments() {
        return listComments;
    }

    public void setListComments(List<CommentDTO> listComments) {
        this.listComments = listComments;
    }

    public boolean isCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(boolean createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }





    
}
