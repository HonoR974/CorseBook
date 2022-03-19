package com.back.springboot.models;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Event {

      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Date dateDebut;

    public Date dateFin;

    public String reusme;

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

    public String getReusme() {
        return reusme;
    }

    public void setReusme(String reusme) {
        this.reusme = reusme;
    }

    

}
