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

    //
    //l'user connecté accepte la demande de l'user via son id 
    //return  les deux user 
    @Override
    public List<User> accepteDemande(long id) {
        // TODO Auto-generated method stub

        User userConnected = securityService.getUser();

        User userAccepted = userRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("aucun user ne correspond a l'id " + id));
     

        //check if userConnected a userAccepted 
        if(isContains(userConnected, userAccepted))
        {
            //ajout du contact 
            addContact(userConnected, userAccepted);

            //suppression de l'invitation 
            deleteInvit(userConnected, userAccepted);
        }
        else
        {
            throw new ResourceNotFoundException("l'user " +
                         userConnected.getUsername() +
                          " n'a pas d'invitation de " + 
                          userAccepted.getUsername() );
            
        }

        List<User> list = new ArrayList<>();
        list.add(userAccepted);
        list.add(userConnected);
        return list;
    }

      //check if userConnected contains userAccepted 
    public boolean isContains(User userConnected, User userAccepted)
    {
        boolean condition = false;

        if (userConnected.getlInvitationContact() != null)
        {
            for ( User user : userConnected.getlInvitationContact())
            {
                if(user.getUsername().equals(userAccepted.getUsername()))
                {
                    condition = true;
                }
            }
            
        }
        else
        {
            condition = false;
         
        }
        return condition;
    }
    

    public void addContact(User userConnected, User userAccepted)
    {

            //de l'userJwt a UserId 
            List<User> listContact = new ArrayList<>();
            listContact.add(userAccepted);
            userConnected.setListContact(listContact);

            //de UserId a UserJwt
            listContact = new ArrayList<>();
            listContact.add(userConnected);
            userAccepted.setListContact(listContact);
    }
    

    public void deleteInvit(User userConnected, User userAccepted)
    {
        List<User> lUsers = new ArrayList<>();
        for (User user : userConnected.getlInvitationContact())
        {
            if(user.getUsername().equals(userAccepted.getUsername()))
            {
                
            }
            else
            {
                lUsers.add(user);
            }
        }

        userConnected.setlInvitationContact(lUsers);
        userRepository.save(userConnected);
    }
}
