package com.back.springboot.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Marker 
{

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private float latitude;

    private float longitude;

    private String label;

    @ManyToOne
    private Event event;

    public Marker() {
    }
    
    

    public Marker(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    

    
}