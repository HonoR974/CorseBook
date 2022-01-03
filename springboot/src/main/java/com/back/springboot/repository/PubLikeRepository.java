package com.back.springboot.repository;


import com.back.springboot.models.PubLike;
import com.back.springboot.models.Publication;
import com.back.springboot.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PubLikeRepository extends JpaRepository<PubLike, Long>{
 
    PubLike findByUserAndPublication(User user, Publication publicaton);
}
