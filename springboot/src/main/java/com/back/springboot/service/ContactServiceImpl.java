package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.User;
import com.back.springboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl  implements ContactService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    //ajout de l'user avec le jwt a l'user avec l'id 
    //dans la liste des invitations de contact 
    @Override
    public User setAskContact(long id) 
    {

        User userDemandant = securityService.getUser();

        User userRecevant = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with id " + id +  " does'nt exist "));

        List<User> listInvitation = new ArrayList<>();

        listInvitation.add(userDemandant);
        userRecevant.setlInvitationContact(listInvitation);
        
        return userRepository.save(userRecevant);
    }

    
    
    
}
