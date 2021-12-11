package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Publication;
import com.back.springboot.models.User;
import com.back.springboot.repository.PublicationRepository;
import com.back.springboot.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {


    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    //----------- CRUD -------------//

    @Override
    public Publication createPublication(Publication publication) {
        
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> getAll() {
        
        return publicationRepository.findAll();
    }

    @Override
    public Publication getById(long id) {
        
    Publication publication = publicationRepository.findById(id) 
                         .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
 

        return publication;
    }

 
    @Override
    public Publication updatePublication(long id, Publication publicationRequest) {
       
        Publication publication = publicationRepository.findById(id) 
                                    .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
 
         publication.setContenu(publicationRequest.getContenu());
         publication.setCountLike(publicationRequest.getCountLike());
                                    
        return publicationRepository.save(publication);
    }

    @Override
    public void deletePublication(long id)
    {
        Publication publication = publicationRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
 
        publicationRepository.delete(publication);
    }

   //------------- Convert DTO -----------//

    // from publication to publicationDTO 
    @Override
    public PublicationDTO convertToDto(Publication publication) {

        PublicationDTO publicationDTO = modelMapper.map(publication, PublicationDTO.class);
        
        publicationDTO.setUsername(securityService.getUsername());

        return publicationDTO;
    }
   
    @Override
    public List<PublicationDTO> convertToDtoList(List<Publication> publications) {
    
        List<PublicationDTO> lDtos = new ArrayList<>();

        for (Publication publication : publications) {

            lDtos.add(convertToDto(publication));
        }
        return lDtos;
    }
    
    @Override
    public Publication convertToEntity(PublicationDTO publicationDTO) {

        Publication publication = modelMapper.map(publicationDTO, Publication.class);

        User user = securityService.getUser();
        publication.setUser(user);

        return publication;
    }

    
}
