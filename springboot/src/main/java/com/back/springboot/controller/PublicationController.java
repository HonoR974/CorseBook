package com.back.springboot.controller;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import java.util.HashMap;
import java.util.Map;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Publication;
import com.back.springboot.service.CommentService;
import com.back.springboot.service.PublicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


  @CrossOrigin
  @RestController
  @RequestMapping("/api/publication/")
  public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private CommentService commentService;

    @GetMapping("user/")
    public ResponseEntity<List<PublicationDTO>> getPublicationsByUser()
    {
        List<Publication> publications = publicationService.getPublicationsByUser();

        List<PublicationDTO> publicationDTOs = publicationService.convertToDtoList(publications);

        return new ResponseEntity<>(publicationDTOs,HttpStatus.ACCEPTED);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<PublicationDTO>> getPublicationsByUserId(@PathVariable long id)
    {
        List<Publication> publications = publicationService.getPublicationsByuserId(id);

        List<PublicationDTO> publicationDTOs = publicationService.convertToDtoList(publications);

        return new ResponseEntity<>(publicationDTOs,HttpStatus.ACCEPTED);
    }
    @GetMapping("public")
    public ResponseEntity<?> getPublicationPublic()
    {
        List<Publication> list = publicationService.getPublicationPublic().stream()
                                  .sorted(Comparator.comparing(Publication::getDateCreate).reversed())
                                  .collect(Collectors.toList());

        if(list == null)
        {
          new ResourceNotFoundException("la liste est null ");

        }

        //
        List<PublicationDTO> lDtos = new ArrayList<>();
        PublicationDTO publicationDTO;
        List<CommentDTO> commentDTOs;

        for(Publication pub : list)
        {
          commentDTOs = new ArrayList<>();
          publicationDTO = new PublicationDTO();


          publicationDTO = publicationService.convertToDto(pub);


          
          if(pub.getListComments() !=null)
          {
            commentDTOs = commentService.convertToDtoList(pub.getListComments());

            publicationDTO.setListComments(commentDTOs);
          }
      
         lDtos.add(publicationDTO);

        }
        return ResponseEntity.ok(lDtos);
    }


    //------------------ Like & DisLike ----------//

    @PutMapping("/liked/{id}")
    public ResponseEntity<?> publicationLikeById(@PathVariable long id)
    {

      Publication publication = publicationService.publicationLiked(id);

      PublicationDTO publicationDTO = publicationService.convertToDto(publication);

      return ResponseEntity.ok(publicationDTO);
    }

    @PutMapping("/disliked/{id}")

    public ResponseEntity<?> publicationDislikedById(@PathVariable long id)
    {

      Publication publication = publicationService.publicationDisliked(id);

      PublicationDTO publicationDTO = publicationService.convertToDto(publication);

      return ResponseEntity.ok(publicationDTO);

    }



  //-------- CRUD Operations -----------------//

  //create 
  @PostMapping()
  public ResponseEntity<?> createPublication(@RequestBody PublicationDTO publicationDTORequest )
  {

    System.out.println("\n publication DTO Request  " + publicationDTORequest.toString()
          +  "\n "+ " \n ");
    
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

  System.out.println("\n update publication " + publication.getListFile().size());

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
