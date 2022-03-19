package com.back.springboot.repository;

import org.springframework.stereotype.Repository;

import com.back.springboot.models.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<Location,Long>{
    

    
}
