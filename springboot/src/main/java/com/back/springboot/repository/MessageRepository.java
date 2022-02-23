package com.back.springboot.repository;
import com.back.springboot.models.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository <Message,Long>{
    
}
