package com.back.springboot.dto;

import lombok.Data;

@Data
public class MarkerDTO {
    
    private long id;


    private float latitude;

    private float longitude;

    private String label;


    
    public MarkerDTO() {
    }

   
    
    public MarkerDTO(float latitude, float longitude) {
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

    
}
