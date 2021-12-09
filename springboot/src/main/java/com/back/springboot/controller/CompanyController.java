package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.dto.CompanyDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Company;
import com.back.springboot.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/")
public class CompanyController {
    
//une company est constitué d'un nom et une adresse ainsi que d'un user 
    @Autowired
    private CompanyService companyService;



    @GetMapping()
    public ResponseEntity<?> getCompanys()
    {
        List<CompanyDTO> list = companyService.convertLstCompToListCompDto(companyService.getAll());

        if(list==null)
        {
            new ResourceNotFoundException("aucune entreprise existe  ");
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping()
    public ResponseEntity<?> createCompany(@RequestBody CompanyDTO companyDTO)
    {
        //lors de la conversion creer une adresse a la company 
        Company companyConverted = companyService.convertToEntity(companyDTO);
        Company company = companyService.createCompany(companyConverted);

       return new ResponseEntity<Company>(company, HttpStatus.CREATED);
    }

/*
    @GetMapping("{id}")
    public ResponseEntity<?> getCompanyByID(@PathVariable long id)
    {
        Company company = companyService.getCompanyById(id);

        if (company== null)
        {
            new ResourceNotFoundException("l'entreprise  : "+ id + " n'existe pas ");
        }

        return ResponseEntity.ok(company);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCompanyByID(@PathVariable long id)
    {
        Company company = companyService.updateCompany( id);

        if (company== null)
        {
            new ResourceNotFoundException("l'entreprise  : "+ id + " n'existe pas ");
        }

        return ResponseEntity.ok(company);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteCompany(@PathVariable long id )
    {
        Company company = companyService.getCompanyById(id);

        if (company== null)
        {
            new ResourceNotFoundException("l'entreprise  : "+ id + " n'existe pas ");
        }

        companyService.deleteCompany(id);

        return HttpStatus.ACCEPTED;
    }

    */
}
