package com.back.springboot.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Adress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 5, max = 5)
    private String codePostal;

    private String ville; 

    private String adresseComplet;

    @OneToOne(mappedBy = "adress")
    private Company company;

    

    public Adress() {
    }

    public Adress(@Size(min = 5, max = 5) String codePostal, String ville, String adresseComplet) {
        this.codePostal = codePostal;
        this.ville = ville;
        this.adresseComplet = adresseComplet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAdresseComplet() {
        return adresseComplet;
    }

    public void setAdresseComplet(String adresseComplet) {
        this.adresseComplet = adresseComplet;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    
    
}
