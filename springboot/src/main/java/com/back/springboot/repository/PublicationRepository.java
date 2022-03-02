package com.back.springboot.repository;

import java.util.List;

import com.back.springboot.models.Publication;
import com.back.springboot.models.Statut;
import com.back.springboot.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository  extends JpaRepository<Publication, Long>{

    List<Publication> findByStatut(Statut statut);

    List<Publication> findByUser(User user);
}
