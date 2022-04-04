package com.back.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class EventDTO {
   
    private long id;

    private String name;

    private String dateDebut;

    private String dateFin;

    private String contenu;

    private List<MarkerDTO> listMarker;

    private List<FileDTO> listFileAPI;

    private List<String> listParticipant;
    
    private List<CommentDTO> listComments;

    private boolean isParticiped;

    public EventDTO() {
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  

    public List<MarkerDTO> getListMarker() {
        return listMarker;
    }

    public void setListMarker(List<MarkerDTO> listMarker) {
        this.listMarker = listMarker;
    }

    public List<FileDTO> getListFileAPI() {
        return listFileAPI;
    }

    public void setListFileAPI(List<FileDTO> listFileAPI) {
        this.listFileAPI = listFileAPI;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListParticipant() {
        return listParticipant;
    }

    public void setListParticipant(List<String> listParticipant) {
        this.listParticipant = listParticipant;
    }

    public List<CommentDTO> getListComments() {
        return listComments;
    }

    public void setListComments(List<CommentDTO> listComments) {
        this.listComments = listComments;
    }

    public boolean isParticiped() {
        return isParticiped;
    }

    public void setParticiped(boolean isParticiped) {
        this.isParticiped = isParticiped;
    }

    

}
