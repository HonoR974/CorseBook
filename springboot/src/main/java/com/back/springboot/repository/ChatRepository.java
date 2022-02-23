package com.back.springboot.repository;

import java.util.List;

import com.back.springboot.models.Chat;
import com.back.springboot.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChatRepository extends JpaRepository<Chat,Long>{
 
    List<Chat> findByUsers(User user);

}
