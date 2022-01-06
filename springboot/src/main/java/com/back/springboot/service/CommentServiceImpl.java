package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Comment;
import com.back.springboot.models.Publication;
import com.back.springboot.models.User;
import com.back.springboot.repository.CommentRepository;
import com.back.springboot.repository.PublicationRepository;
import com.back.springboot.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl  implements CommentService{
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private CommentRepository commentRepository;


    //-------- CRUD -------// 

    @Override
    public Comment createComment(Comment comment) {
       
        
        return commentRepository.save(comment);
    }



    //-------- CONVERT DTO -----//

    
    @Override
    public Comment convertToEntity(CommentDTO commentDTO) {

   
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        User user = userRepository.findByUsername(commentDTO.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException(
                "User with username " + commentDTO.getUsername() + " is null"));

        comment.setUser(user);        

        Publication publication = publicationRepository.findById(commentDTO.getId_publication())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Publication with id  " + commentDTO.getId_publication() + " doesnt exist "));

        comment.setPublications(publication);            

        return comment;
    }

    @Override
    public CommentDTO convertToDto(Comment comment) {
        
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        commentDTO.setId_publication(comment.getPublication().getId());

        commentDTO.setUsername(comment.getUser().getUsername());

        return commentDTO;
    }

    @Override
    public List<CommentDTO> convertToDtoList(List<Comment> listComment) {
        // TODO Auto-generated method stub
        return null;
    }

  
    

}
