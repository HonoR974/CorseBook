package com.back.springboot.dto;



import lombok.Data;

@Data
public class CompanyDTO {
    
    private long id;

    private String name;

    private String codePostal;

    private String ville;

   private String infoAdress;


    //la definition de l'addresse 
    //code postal -> ville -> adreseComplete 
    public void setAdresse(String postalCode, String city, String completeAdresse)
    {
        this.codePostal = postalCode;
        this.ville = city;
        this.infoAdress = completeAdresse;
    }

 

    public CompanyDTO() {
    }



    //get & set 
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getInfoAdress() {
        return infoAdress;
    }

    public void setInfoAdress(String infoAdress) {
        this.infoAdress = infoAdress;
    }

    @Override
    public String toString() {
        return "CompanyDTO [codePostal=" + codePostal + ", id=" + id + ", infoAdress=" + infoAdress + ", name=" + name
                + ", ville=" + ville + "]";
    }




    
    
}
