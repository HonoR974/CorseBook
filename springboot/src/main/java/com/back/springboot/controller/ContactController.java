package com.back.springboot.controller;



import com.back.springboot.service.ContactService;
import com.back.springboot.service.UserService;

import java.util.List;
import com.back.springboot.models.User;
import com.back.springboot.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


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
    public ResponseEntity<List<UserDTO>> accepteDemande(@PathVariable long id)
    {

        List<User> list = contactService.accepteDemande(id);

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);

    }




     //refuse la demande d'ajout recu 

    
}
