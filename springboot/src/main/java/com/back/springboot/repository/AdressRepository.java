package com.back.springboot.repository;

import com.back.springboot.models.Adress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress,Long>{
    
}
