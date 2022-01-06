package com.back.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.models.Comment;
import com.back.springboot.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // ------------------- CRUD ------------------//

    // create by publication id
    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.createComment(commentService.convertToEntity(commentDTO));

        CommentDTO commentDTOSend = commentService.convertToDto(comment);

        return ResponseEntity.ok(commentDTOSend);
    }

    // get all
    @GetMapping()
    public ResponseEntity<?> getAllComments() {
        List<CommentDTO> listDTO = commentService.convertToDtoList(commentService.getAll());

        return ResponseEntity.ok(listDTO);
    }

    // get by id
    @GetMapping("{id}")
    public ResponseEntity<?> getCommentByID(@PathVariable long id) {
        CommentDTO commentDTO = commentService.convertToDto(commentService.getById(id));

        return ResponseEntity.ok(commentDTO);
    }

    // update
    @PutMapping("{id}")
    public ResponseEntity<?> updateCommentByID(@PathVariable long id,
            @RequestBody CommentDTO commentDTORequest) 
    {
        Comment comment = commentService.updateComment(id, commentService.convertToEntity(commentDTORequest));

        CommentDTO commentDTO = commentService.convertToDto(comment);
        
        return ResponseEntity.ok(commentDTO);
    }

    //delete 
    @DeleteMapping("{id}")
    public ResponseEntity <Map<String, Boolean>> deleteCommentById(@PathVariable long id)
    {
        commentService.deleteById(id);
        
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
