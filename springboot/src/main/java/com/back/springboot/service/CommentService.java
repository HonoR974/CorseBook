package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.models.Comment;

public interface CommentService {
    
    Comment createCommentByPublicationID(long id,Comment commentRequest);

    Comment commentLiked(long id);

    //  CRUD //
    List<Comment> getAll();

    Comment getById(long id);
    
    Comment createComment(Comment comment);

    Comment updateComment(long id, Comment comment);

    void deleteById(long id);

    // convert DTO //
    Comment convertToEntity(CommentDTO commentDTO);

    CommentDTO convertToDto(Comment comment);

    List<CommentDTO> convertToDtoList(List<Comment> listComment);


}
