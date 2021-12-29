package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.models.Publication;

public interface PublicationService {


    List<Publication> getPublicationPublic();

    Publication publicationLiked(long id);

    //CRUD 
    Publication createPublication(Publication publication);

    List<Publication> getAll();

    Publication getById(long id);

    Publication updatePublication(long id, Publication Publication);

    void deletePublication(long id);
    //convert DTO 

    PublicationDTO convertToDto(Publication publication);

    List<PublicationDTO> convertToDtoList(List<Publication> publications);

    Publication convertToEntity(PublicationDTO publicationDTO);
    

}
