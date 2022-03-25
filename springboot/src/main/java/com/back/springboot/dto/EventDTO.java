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

    @Override
    public String toString() {
        return "EventDTO [contenu=" + contenu + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", id=" + id
                + ", listFileAPI=" + listFileAPI + ", listMarker=" + listMarker + ", name=" + name + "]";
    }


}
