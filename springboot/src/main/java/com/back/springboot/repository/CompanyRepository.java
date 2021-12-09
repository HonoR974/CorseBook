package com.back.springboot.repository;

import com.back.springboot.models.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long>{
    
}
