package com.back.springboot.service;

import java.util.ArrayList;
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

    //create comment in publication 
    @Override
    public Comment createCommentByPublicationID(long id,Comment commentRequest)
    {
        Comment comment = new Comment();
        comment.setContenu(commentRequest.getContenu());
        comment.setUser(commentRequest.getUser());

        //ajout du commentaire a la publication 
        Publication publication = publicationRepository.findById(id)  .orElseThrow(() -> new ResourceNotFoundException(
            "la publication avec l'id " + id + " n'existe pas "));

        List<Comment> list = new ArrayList<>();
        list.add(commentRequest);
        publication.setListComments(list);
        publicationRepository.save(publication);

        //ajout de la publication au commentaire 
        comment.setPublications(publication);

        return     commentRepository.save(comment);
    }


    //-------- CRUD -------// 

    //create 
    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    //get all 
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }


    //get by id 
    @Override
    public Comment getById(long id)
    {
        Comment comment = commentRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException(
                    "le commentaire avec l'id " + id + " n'existe pas "));

        return comment;
    }

    //update 
    @Override
    public  Comment updateComment(long id, Comment commentRequest)
    {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "le commentaire avec l'id " + id + " n'existe pas "));
         
        comment.setContenu(commentRequest.getContenu());
        comment.setUser(commentRequest.getUser());
        comment.setPublications(commentRequest.getPublication());    
        
        return commentRepository.save(comment);
    }

    //delete 
    public void deleteById(long id)
    {
        Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "le commentaire avec l'id " + id + " n'existe pas "));

        commentRepository.delete(comment);
    }


    //-------- CONVERT DTO -----//

    
    @Override
    public Comment convertToEntity(CommentDTO commentDTO) {

   
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        User user = userRepository.findByUsername(commentDTO.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException(
                "User with username " + commentDTO.getUsername() + " is null"));

        comment.setUser(user);        

        if (commentDTO.getId_publication() != 0 )
        {
            Publication publication = publicationRepository.findById(commentDTO.getId_publication())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Publication with id  " + commentDTO.getId_publication() + " doesnt exist "));

          comment.setPublications(publication);            

        }

       
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
  
        List<CommentDTO> lDtos = new ArrayList<>();
        
        for(Comment comment : listComment)
        {
            CommentDTO commentDTO = convertToDto(comment);
            lDtos.add(commentDTO);

        }
        return lDtos;
    }

  
    

}
