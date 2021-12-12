package com.back.springboot.repository;


import java.util.Optional;

import com.back.springboot.models.Statut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut,Long>{
    
    Optional<Statut> findByName(String name);
}
