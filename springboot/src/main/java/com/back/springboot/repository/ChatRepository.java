package com.back.springboot.repository;

import com.back.springboot.models.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChatRepository extends JpaRepository<Chat,Long>{
    
}
