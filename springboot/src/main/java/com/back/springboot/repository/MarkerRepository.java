package com.back.springboot.repository;

import org.springframework.stereotype.Repository;

import com.back.springboot.models.Marker;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {

}
