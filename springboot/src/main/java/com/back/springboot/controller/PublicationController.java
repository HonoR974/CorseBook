package com.back.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Publication;
import com.back.springboot.service.PublicationService;

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
  @RequestMapping("/api/publication/")
  public class PublicationController {

    @Autowired
    private PublicationService publicationService;

  //-------- CRUD Operations -----------------//

  //create 
  @PostMapping()
  public ResponseEntity<?> createPublication(@RequestBody PublicationDTO publicationDTORequest )
  {
    Publication publication  = publicationService.createPublication( 
                              publicationService.convertToEntity(publicationDTORequest));
    
    PublicationDTO publicationDTO = publicationService.convertToDto(publication);
    return new ResponseEntity<PublicationDTO>(publicationDTO, HttpStatus.ACCEPTED);
  }

  //get all 
  @GetMapping()
  public ResponseEntity<?> getAllPublication()
  {
    List<Publication> list = publicationService.getAll();
    List<PublicationDTO> listDto = publicationService.convertToDtoList(list);

  

    return new ResponseEntity<List<PublicationDTO>>(listDto, HttpStatus.ACCEPTED);

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

    PublicationDTO publicationDTO = publicationService.convertToDto(publication);

    return new ResponseEntity<PublicationDTO>(publicationDTO, HttpStatus.ACCEPTED);

  }
  

  //update by id 
  @PutMapping("{id}")
  public ResponseEntity<PublicationDTO> updatePublicationById(@PathVariable long id,
                                                @RequestBody PublicationDTO publicationDTORequest)
  {

  Publication publication = publicationService.updatePublication
            (id, publicationService.convertToEntity(publicationDTORequest));

  PublicationDTO publicationDTO = publicationService.convertToDto(publication);
  return new ResponseEntity<PublicationDTO>(publicationDTO, HttpStatus.ACCEPTED);
  }

  //delete by id 

  @DeleteMapping("{id}")
  public ResponseEntity< Map<String, Boolean>> deletePublicationById(@PathVariable long id)
  {

    publicationService.deletePublication(id);


  Map<String,Boolean> response = new HashMap<>();
      response.put("deleted", Boolean.TRUE);
      return ResponseEntity.ok(response);
  }


    //-------- CRUD Operations -----------------//



}
