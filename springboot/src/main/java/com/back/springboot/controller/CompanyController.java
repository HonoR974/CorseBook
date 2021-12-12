package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.dto.CompanyDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Company;
import com.back.springboot.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;




    //---------------- CRUD Operations ---------//

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
        

        CompanyDTO companyDTOSend = companyService.convertTodDto(company);

        System.out.println("\n companyDTO " + companyDTOSend.toString());
       return new ResponseEntity<CompanyDTO>(companyDTOSend, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getCompanyByID(@PathVariable long id)
    {
        Company company = companyService.getCompanyById(id);

  
        
        if (company== null)
        {
            new ResourceNotFoundException("l'entreprise  : "+ id + " n'existe pas ");
        }

       
        CompanyDTO companyDTO = companyService.convertTodDto(company);
        
        return ResponseEntity.ok(companyDTO);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateCompanyByID(@PathVariable long id, @RequestBody CompanyDTO companyDTO)
    {
      Company companyRequest = companyService.convertToEntity(companyDTO);

      Company company = companyService.updateCompany(id, companyRequest);


        if (company== null)
        {
            new ResourceNotFoundException("l'entreprise  : "+ id + " n'existe pas ");
        }

        CompanyDTO compSendDTO = companyService.convertTodDto(company);

        return ResponseEntity.ok(compSendDTO);
    }


    @DeleteMapping("{id}")
    public HttpStatus deleteCompany(@PathVariable long id )
    {
        companyService.deleteCompany(id);


        return HttpStatus.ACCEPTED;
    }



}
