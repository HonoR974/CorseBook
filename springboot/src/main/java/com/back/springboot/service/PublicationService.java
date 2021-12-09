package com.back.springboot.service;

import java.util.List;

import com.back.springboot.models.Publication;

public interface PublicationService {

    Publication createPublication(Publication publication);

    List<Publication> getAll();

    Publication getById(long id);
    
}
