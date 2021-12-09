package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Publication;
import com.back.springboot.service.PublicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/publication/")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    //create 
    @PostMapping()
   public ResponseEntity<?> createPublication(@RequestBody PublicationDTO publicationDTO )
   {

        Publication publication  = publicationService.createPublication(new Publication(publicationDTO.getContenu()));

        return new ResponseEntity<Publication>(publication, HttpStatus.ACCEPTED);
   }

   //get all 
   @GetMapping()
   public ResponseEntity<?> getAllPublication()
   {

     return new ResponseEntity<List<Publication>>(publicationService.getAll(), HttpStatus.ACCEPTED);

   }

   //get by id 

   @GetMapping("{id}")
   public ResponseEntity<?> getPublicationByID(@PathVariable long id)
   {

     Publication publication = publicationService.getById(id);

     if(publication == null )
     {
          new ResourceNotFoundException("la publication : "+ id + " n'existe pas ");
     }

     return new ResponseEntity<Publication>(publication, HttpStatus.ACCEPTED);

   }
    
}
