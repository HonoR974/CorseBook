package com.back.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.back.springboot.dto.FileDTO;
import com.back.springboot.dto.PublicationDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.File;
import com.back.springboot.models.Publication;
import com.back.springboot.models.Statut;
import com.back.springboot.models.User;
import com.back.springboot.repository.FileRepository;
import com.back.springboot.repository.PublicationRepository;
import com.back.springboot.repository.StatutRepository;
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



    @Override
    public List<Publication> getPublicationPublic() {
        
        Statut statut = statutRepository.findByName("public")
        .orElseThrow( () -> new ResourceNotFoundException("Le statut : public n'existe pas " ));

        return publicationRepository.findByStatut(statut);
    }


    @Override
    public Publication publicationLiked(long id)
    {
        Publication publication = publicationRepository.findById(id)
                                   .orElseThrow( () -> new ResourceNotFoundException("La publication n'existe pas " ));

        publication.setCountLike(publication.getCountLike() + 1 );

        return publicationRepository.save(publication);

    }


    //----------- CRUD -------------//

    @Override
    public Publication createPublication(Publication publication) {
        
        //statut public 
        Statut statut = statutRepository.findByName("public")
                                        .orElseThrow( () -> new ResourceNotFoundException("Le statut : public n'existe pas " ));
        publication.setStatut(statut);

        //date 
        publication.setDateCreate(new Date() );

        //files 
        if(publication.getListFile() != null)
        {
            List<File> files = publication.getListFile();
            fileRepository.saveAll(files);
        }
     
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> getAll() {
        
        return publicationRepository.findAll();
    }

    @Override
    public Publication getById(long id) {
        
    Publication publication = publicationRepository.findById(id) 
                         .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
 

        return publication;
    }

 
    @Override
    public Publication updatePublication(long id, Publication publicationRequest) {
       
        Publication publication = publicationRepository.findById(id) 
                                    .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
 
         publication.setContenu(publicationRequest.getContenu());
         publication.setCountLike(publicationRequest.getCountLike());
                                    
        return publicationRepository.save(publication);
    }

    @Override
    public void deletePublication(long id)
    {
        Publication publication = publicationRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Product not extist with id : "+ id) ) ;
                            
        publicationRepository.delete(publication);
    }

   //------------- Convert DTO -----------//

    // from publication to publicationDTO 
    @Override
    public PublicationDTO convertToDto(Publication publication) {

        PublicationDTO publicationDTO = modelMapper.map(publication, PublicationDTO.class);
        
        publicationDTO.setUsername(publication.getUser().getUsername());

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

        User user = securityService.getUser();
        publication.setUser(user);

       
        //si la publication DTO contient des fichiers 
        if(publicationDTO.getListFile() != null)
        {
            List<File> list = new ArrayList<>();
          
            for(FileDTO fileDTO : publicationDTO.getListFile())
            {
                File file = new File(fileDTO.getUrl(), fileDTO.getName());
                list.add(file);

            }
            publication.setListFile(list);
        }


        return publication;
    }

    
}
