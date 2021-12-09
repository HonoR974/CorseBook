package com.back.springboot.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    
    private Long id;

    private String name;

    private String codePostal;

    private String ville;

    private String adressComplet;


    //la definition de l'addresse 
    //code postal -> ville -> adreseComplete 
    public void setAdresse(String postalCode, String city, String completeAdresse)
    {
        this.codePostal = postalCode;
        this.ville = city;
        this.adressComplet = completeAdresse;
    }



    //get & set 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresseComplete() {
        return adressComplet;
    }

    public void setAdresseComplete(String adresseComplete) {
        this.adressComplet = adresseComplete;
    }



    @Override
    public String toString() {
        return "CompanyDTO [adresseComplete=" + adressComplet + ", codePostal=" + codePostal + ", id=" + id
                + ", name=" + name + ", ville=" + ville + "]";
    }

    
    
}
