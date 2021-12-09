package com.back.springboot.dto;

import lombok.Data;

@Data
public class PublicationDTO {
    
    private String contenu;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

   

    
}
