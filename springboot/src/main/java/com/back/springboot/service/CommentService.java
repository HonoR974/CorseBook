package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.models.Comment;

public interface CommentService {
    
    //  CRUD //
    
    Comment createComment(Comment comment);

    // convert DTO //
    Comment convertToEntity(CommentDTO commentDTO);

    CommentDTO convertToDto(Comment comment);

    List<CommentDTO> convertToDtoList(List<Comment> listComment);
}
