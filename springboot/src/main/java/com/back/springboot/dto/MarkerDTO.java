package com.back.springboot.dto;

import lombok.Data;

@Data
public class MarkerDTO {
    
    private long id;


    private long latitude;

    private long longitude;

    private String label;


    
    public MarkerDTO() {
    }

    public MarkerDTO(long lat, long lng)
    {
        this.latitude = lat;
        this.longitude = lng;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    
}
