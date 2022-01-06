package com.back.springboot.controller;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.models.Comment;
import com.back.springboot.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //------------------- CRUD ------------------//

    //create by publication id 
    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO)
    {
        Comment comment = commentService.createComment(commentService.convertToEntity(commentDTO));


        CommentDTO commentDTOSend = commentService.convertToDto(comment);

        return ResponseEntity.ok(commentDTOSend);
    }
    
}
