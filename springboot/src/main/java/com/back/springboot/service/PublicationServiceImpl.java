package com.back.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.dto.FileDTO;
import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Comment;
import com.back.springboot.models.File;
import com.back.springboot.models.Publication;
import com.back.springboot.models.Statut;
import com.back.springboot.models.User;
import com.back.springboot.repository.CommentRepository;
import com.back.springboot.repository.FileRepository;
import com.back.springboot.repository.PublicationRepository;
import com.back.springboot.repository.StatutRepository;
import com.back.springboot.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CommentRepository commentRepository;



    //get public publication 
    @Override
    public List<Publication> getPublicationPublic() {

        Statut statut = statutRepository.findByName("public")
                .orElseThrow(() -> new ResourceNotFoundException("Le statut : public n'existe pas "));

                
        return publicationRepository.findByStatut(statut);
    }


    //Like 
    @Override
    public Publication publicationLiked(long id) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La publication n'existe pas "));

        User user = securityService.getUser();

        //si l'user a deja like la pub 
        //il ne peut pas la liker a nouveau 
        
        List<User> list = publication.getLikeUser();


        if (list != null && !list.contains(user)) {
                
            list.add(user);
            publication.setLikeUser(list);

            publication.setCountLike(publication.getCountLike() + 1);
        }

        return publicationRepository.save(publication);

    }

    //disliked 
    @Override
    public Publication publicationDisliked(long id) 
    {
        Publication publication = publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("La publication n'existe pas "));

        User user = securityService.getUser();

        //si l'user a deja like la pub 
        //il ne peut pas la liker a nouveau 
        List<User> list = publication.getLikeUser();

        if(list !=null && !list.contains(user))
        {
            new ResourceNotFoundException("L'user " + user.getUsername()+ 
                                "n'a pas liké la publication " + publication.getId() );
        }
        else
        {

            //pub 
            list.remove(user);
            publication.setLikeUser(list);
            publicationRepository.save(publication);

            //user 
            List<Publication> lPublications = user.getPublicationsLiked();
            lPublications.remove(publication);
            user.setPublicationsLiked(lPublications);
         
           
        }

        return publication;
    }



    //verifie si l'user n'a pas deja liké la pub 
    //true = liked 
    //false = no liked by user 
    public boolean checkLikeByUserAndPub(Publication publicationRequest)
    {
        List<User> list = publicationRequest.getLikeUser();

        boolean condition = false;
        if (list != null)
        {
            if(  !list.contains(securityService.getUser())  )
            {
                System.out.println("\n la pub n' a pas été liked par " + publicationRequest.getUser().getUsername());
                condition = false;
            }
            else
            {
                condition = true;
            }
        }
        

       
        
        return condition;
    }




    // ----------- CRUD -------------//




    @Override
    public Publication createPublication(Publication publication) {

        // statut public
        Statut statut = statutRepository.findByName("public")
                .orElseThrow(() -> new ResourceNotFoundException("Le statut : public n'existe pas "));
        publication.setStatut(statut);

        // date
        publication.setDateCreate(new Date());


        // repo
        publicationRepository.save(publication);

        // file
        if (publication.getListFile() != null) {
            fileRepository.saveAll(publication.getListFile());
        }

        return publication;
    }

    @Override
    public List<Publication> getAll() {

        return publicationRepository.findAll();
    }

    @Override
    public Publication getById(long id) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : " + id));


        return publication;
    }

    @Override
    public Publication updatePublication(long id, Publication publicationRequest) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : " + id));

        publication.setContenu(publicationRequest.getContenu());
        
        if ( publicationRequest.getListFile() != null)
        {

            //delete les anciens fichiers 
            for (File filedelete : publication.getListFile()) 
            {
           
                fileRepository.deleteById(filedelete.getId());
            }
            
            File fileIndex ;
            List<File> lFiles = new ArrayList<>();
            for (File fileRequest :  publicationRequest.getListFile())
            {
           
                fileIndex = new File();
                fileIndex.setName(fileRequest.getName());
                fileIndex.setUrl(fileRequest.getUrl());
                fileIndex.setPublication(publication);
                fileRepository.save(fileIndex);
                lFiles.add(fileIndex);
            }
            
            publication.setListFile(lFiles);
        }
        
        publication.setCountLike(publicationRequest.getCountLike());
        return publicationRepository.save(publication);
    }

    @Override
    public void deletePublication(long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : " + id));

        Statut statut = statutRepository.findByName("deleted")
        .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : " + id));

        publication.setStatut(statut);

        publicationRepository.save(publication);

    }

    public void deleteFile(Publication  publication)
    {
        for(File file : publication.getListFile())
        {
            fileRepository.deleteById(file.getId());
        }
    }

    public void deleteComments(Publication publication)
    {
        for (Comment comment : publication.getListComments())
        {
            commentRepository.delete(comment);
        }
    }

    // ------------- Convert DTO -----------//


    //from publication to list Comment DTO 
    public List<CommentDTO> getCommentsDTOByPublication(Publication publication)
    {
        List<CommentDTO> list = new ArrayList<>();

        for (Comment comment : publication.getListComments())
        {
            CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

            commentDTO.setId_publication(comment.getPublication().getId());

            commentDTO.setUsername(comment.getUser().getUsername());

            list.add(commentDTO);
        }



        return list;
    }



    // from publication to publicationDTO
    @Override
    public PublicationDTO convertToDto(Publication publication) {

        
        PublicationDTO publicationDTO = modelMapper.map(publication, PublicationDTO.class);

        publicationDTO.setUsername(publication.getUser().getUsername());



        // l'user doit etre connecté pour savoir 
        //si il a deja like la publication 
        if(!securityService.isAuthenticated())
        {
            publicationDTO.setLiked(false);
        }
        else
        {
            publicationDTO.setLiked(checkLikeByUserAndPub(publication));
        }


        if (publicationDTO.getListComments()!= null)
        {
            publicationDTO.setListComments(getCommentsDTOByPublication(publication));
        }
    

        //createdByUser
        //si l'user qui l'a créer et celui qui est connecté 

        if(securityService.isAuthenticated() && publication.getUser().getUsername().equals(securityService.getUser().getUsername()))
        {
            publicationDTO.setCreatedByUser(true);
        }
        else
        {
            publicationDTO.setCreatedByUser(false);
        }

        //statut 
        publicationDTO.setStatut(publication.getStatut().getNom());

        return publicationDTO;
    }

    @Override
    public List<PublicationDTO> convertToDtoList(List<Publication> publications) {

        List<PublicationDTO> lDtos = new ArrayList<>();

        for (Publication publication : publications) {

            lDtos.add(convertToDto(publication));
        }
        return lDtos;
    }

    @Override
    public Publication convertToEntity(PublicationDTO publicationDTO) {

        Publication publication = modelMapper.map(publicationDTO, Publication.class);


        if ( publicationDTO.getUsername() != null)
        {
            User user = userRepository.findByUsername(publicationDTO.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException(
                    "User with username " + publicationDTO.getUsername() + " is null"));

            publication.setUser(user);

        }
     
        // si la publication DTO contient des fichiers
        if (publicationDTO.getListFile() != null) {
            List<File> list = new ArrayList<>();

            for (FileDTO fileDTO : publicationDTO.getListFile()) {
                File file = new File(fileDTO.getUrl(), fileDTO.getName());
                file.setPublication(publication);
                list.add(file);

            }
            publication.setListFile(list);
        }

        return publication;
    }

}
