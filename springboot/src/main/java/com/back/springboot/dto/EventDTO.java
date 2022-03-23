package com.back.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class EventDTO {
   
    private long id;

    private String dateDebut;

    private String dateFin;

    private String resume;

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

  
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
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

    @Override
    public String toString() {
        return "EventDTO [dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", id=" + id + ", listFileAPI="
                + listFileAPI + ", listMarker=" + listMarker + ", resume=" + resume + "]";
    }



    


}
