package com.back.springboot.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

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

    @Autowired
    private SecurityService securityService;



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


    //comment liked 
    @Override
    public Comment commentLiked(long id)
    {
        Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "le commentaire avec l'id " + id + " n'existe pas "));

            //verifie que l'user n'a pas deja like ce commentaire
        User user = securityService.getUser();
        System.out.println("\n user " +  user.toString());

        
        
        if (!comment.getLikeUser().contains(user))
        {
            System.out.println("\n commentLike null ");


            // save comment 
            List<User> list = comment.getLikeUser();
            list.add(user);
            comment.setLikeUser(list);
            commentRepository.save(comment);


            //save user 
            List<Comment> listLiked = user.getCommentsLiked();
            listLiked.add(comment);
            user.setCommentsLiked(listLiked);
            userRepository.save(user);

            //publication 
            Publication publication = comment.getPublication();
 
            List<Comment> lComments  = new ArrayList<>();
            for(Comment comm : publication.getListComments())
            {
                if(comm.getId() != comment.getId())
                {
                    lComments.add(comm);
                }
                else
                {
                    lComments.add(comment);
                }
            }
            publication.setListComments(lComments);

            publicationRepository.save(publication);

            
        }
        else
        {
            System.out.println("\n le commentaire a deja ete aime ");

            throw new ResourceNotFoundException(
                "l'user " + user.getUsername()  + " a deja liké le commentaire " + comment.getId());
        }



        return comment;
    }




    public boolean checkLikeByUserAndCom(Comment commentRequest)
    {
        
       boolean condition = false;

       User user = securityService.getUser();

       List<User> list = commentRequest.getLikeUser();

       if (list != null && list.contains(user))
       {
           condition = true;
       }

        return condition;
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

        //user 
        User user = userRepository.findByUsername(commentDTO.getUsername())
        .orElseThrow(() -> new ResourceNotFoundException(
                "User with username " + commentDTO.getUsername() + " is null"));

        comment.setUser(user);        

        //publication 
        if (commentDTO.getId_publication() != 0 )
        {
            Publication publication = publicationRepository.findById(commentDTO.getId_publication())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Publication with id  " + commentDTO.getId_publication() + " doesnt exist "));

          comment.setPublications(publication);            

        }

        //date 
        if(commentDTO.getDateCreated() != null)
        {
            //dateCreated 
            try {
                Date dateCreated = new SimpleDateFormat("dd/MM/yyyy").parse(commentDTO.getDateCreated());
                comment.setDateCreated(dateCreated);
            
            } catch (ParseException e) {

                System.out.println("\n il n'a pas de date ");
        
                e.printStackTrace();
            }
                
        }
     



        return comment;
    }

    @Override
    public CommentDTO convertToDto(Comment comment) {
        
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        //publication 
        if(comment.getPublication() != null)
        {
            commentDTO.setId_publication(comment.getPublication().getId());

        }

        //username 
        commentDTO.setUsername(comment.getUser().getUsername());

        //date 
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        commentDTO.setDateCreated(dateFormat.format(date));


        //like count 
        if( comment.getLikeUser() == null )
        {
            System.out.println("\n comment.getlCommentLike() == null  ");
            commentDTO.setCountLike(0);
        }
        else
        {
            System.out.println("\n nmb like comment " + comment.getLikeUser().size() );
            commentDTO.setCountLike(comment.getLikeUser().size());
        }

        //liked by user ? 
        commentDTO.setLiked(checkLikeByUserAndCom(comment));


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
