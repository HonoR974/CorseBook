package com.back.springboot.repository;

import com.back.springboot.models.Publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository  extends JpaRepository<Publication, Long>{
    
    Publication findById(long id);
}
