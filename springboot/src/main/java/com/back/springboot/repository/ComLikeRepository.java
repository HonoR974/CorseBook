package com.back.springboot.repository;


import com.back.springboot.models.Comment;
import com.back.springboot.models.CommentLike;
import com.back.springboot.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComLikeRepository  extends JpaRepository<CommentLike, Long>{
    
    CommentLike findByUserAndComment(User user, Comment comment);
}
