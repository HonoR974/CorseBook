package com.back.springboot.controller;



import com.back.springboot.service.ContactService;
import com.back.springboot.service.UserService;

import java.util.List;

import com.back.springboot.models.User;
import com.back.springboot.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/contact/")
public class ContactController {


    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<?> getAllUser()
    {
        List<User> lUsers = userService.getAllUser();

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return ResponseEntity.ok(lDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
       
       User user = userService.getUserById(id);

       UserDTO userDTO = userService.convertToDto(user);
       
        return ResponseEntity.ok(userDTO);
    }
    

    //envoie une demande d'ajout a un user par son id 
    //renvoie l'user ayant recu l'invitation 
    @PostMapping("{id}")
    public ResponseEntity<UserDTO> askForAddContactById(@PathVariable long id)
    {
        
        User userRecevant = contactService.setAskContact(id);

        UserDTO userDTO = userService.convertToDto(userRecevant);


        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }


    //accepte la demande d'ajout recu 
    // l'user connécté avec le jwt accepte l'user a l'id envoyé 
    //renvoie l'user ayant recu l'invitation donc celui qui accepte 
    @PostMapping("/accept/{id}")
    public ResponseEntity<List<UserDTO>> accepteDemandeByID(@PathVariable long id)
    {

        List<User> list = contactService.accepteDemande(id);

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);

    }

     //refuse la demande d'ajout recu 
     //refuse la demande de l'user correspondant à l'id envoyé 
    @DeleteMapping("/refuse/{id}")
    public ResponseEntity<UserDTO> refuseDemandeById(@PathVariable long id)
    {
        User user = contactService.refuseDemandeById(id);

        UserDTO userDTO = userService.convertToDto(user);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }

     //affiche la list des invitations par le jwt 
     @GetMapping("/invitations")
     public ResponseEntity<List<UserDTO>> getInvitationByJwt()
     {
        List<User> list = contactService.getInvitation();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
     }

     
     //affiche la list des invitations par l'id de l'user 
     @GetMapping("/invitations/{id}")
     public ResponseEntity<List<UserDTO>> getInvitationById(@PathVariable long id)
     {
        List<User> list = contactService.getInvitationById(id);

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
     }


     //affiche la list de contacte par l'id 
     @GetMapping("/list/{id}")
     public ResponseEntity<List<UserDTO>> getContactsById(@PathVariable long id)
     {

        List<User> lUsers = contactService.getConctactsById(id);

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
        
     }

      //affiche la list de contacte par jwt 

      @GetMapping("/list")
      public ResponseEntity<List<UserDTO>> getContatcsByJwt()
      {
          
        List<User> lUsers = contactService.getContactsByJwt();

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
      }



   // supprime un contact 
   // id user qui possede la liste de contact 
   // idToDeleted user de la liste de contact 
    @DeleteMapping("/list/{id}/delete/{idToDelete}")
    public ResponseEntity<HttpStatus> deleteContactByID(@PathVariable long id,
                                            @PathVariable long idToDelete)
    {
        contactService.deleteContactById(id, idToDelete);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


   // supprime un contact by jwt 
   // id user de la liste de contact 
   @DeleteMapping("list/delete/{id}")
   public ResponseEntity<HttpStatus> deleteContactByJwt(@PathVariable long id)
   {    
    contactService.deletedContactByJwt(id);
   
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }    
    

    //get Suggest Contact 
    @GetMapping("suggest")
    public ResponseEntity<?> getSuggestContact()
    {
        List<User> list = contactService.getSuggestContact();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }
}
