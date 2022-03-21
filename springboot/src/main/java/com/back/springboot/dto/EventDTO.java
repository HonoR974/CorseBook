package com.back.springboot.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EventDTO {
   
    private long id;

    private  Date dateDebut;

    private Date dateFin;

    private String resume;

    private List<MarkerDTO> listMarker;

    
    public EventDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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

    @Override
    public String toString() {
        return "EventDTO [dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", id=" + id + ", listMarker=" + listMarker
                + ", resume=" + resume + "]";
    }


    


}
