package com.back.springboot.models;



import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Event {

      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Date dateDebut;

    private Date dateFin;

    private String resume;

    @OneToMany( mappedBy = "event")
    private List<Marker> listMarkers;


    public Event() {
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

    public List<Marker> getListMarkers() {
        return listMarkers;
    }

    public void setListMarkers(List<Marker> listMarkers) {
        this.listMarkers = listMarkers;
    }

   

    

}
