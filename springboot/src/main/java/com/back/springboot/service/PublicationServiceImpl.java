package com.back.springboot.service;

import java.util.List;

import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Publication;
import com.back.springboot.repository.PublicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

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
        
        Publication publication = publicationRepository.findById(id);

      

        return publication;
    }

    
    
}
