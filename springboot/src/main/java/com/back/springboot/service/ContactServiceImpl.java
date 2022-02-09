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
        userRecevant.setListInvitation(listInvitation);
        
        return userRepository.save(userRecevant);
    }

    //
    //l'user connecté accepte la demande de l'user via son id 
    //return  les deux user 
    @Override
    public List<User> accepteDemande(long id) {


        User userConnected = securityService.getUser();

        User userAccepted = userRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("aucun user ne correspond a l'id " + id));
     

        //check if user1 a demandé user 2 
        if(isContains(userConnected,userAccepted))
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

      //check if user 1 a demandé user 2 
    public boolean isContains(User user, User userlist)
    {
        boolean condition = false;


        if (user.getListInvitation() != null)
        {
          
             condition = user.getListInvitation().contains(userlist);
    
            
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
            List<User> listContact = userConnected.getListContact();
            listContact.add(userAccepted);
            userConnected.setListContact(listContact);

            //de UserId a UserJwt
            listContact = userAccepted.getListContact();
            listContact.add(userConnected);
            userAccepted.setListContact(listContact);
    }
    

    //supprime l'invitation de l'user2 pour l'user 1 
    // puis de l'user 1 a l'user 2 par securité 
    public void deleteInvit(User userConnected, User userAccepted)
    {
        List<User> lUsers = userAccepted.getListInvitation();
        if(lUsers.contains(userConnected))
        {
            lUsers.remove(userConnected);
            userAccepted.setListInvitation(lUsers);

        }

        List<User> lUsers2 = userConnected.getListInvitation();
        if(lUsers.contains(userAccepted))
        {
            lUsers2.remove(userAccepted);
            userConnected.setListInvitation(lUsers2);
        }
   
        userRepository.save(userAccepted);
        userRepository.save(userConnected);
    
    }

    @Override
    public List<User> getInvitation() {

        User user = securityService.getUser();
        if (user.getListInvitation() != null)
        {
            return user.getListInvitation();
        }
        else
        {
         throw new ResourceNotFoundException("L'user " + user.getUsername() 
                                + " n'a pas d'invitation de contact ");
        }
    }

    @Override
    public List<User> getInvitationById(long id) {
   
        User user = userRepository.findById(id)           
                     .orElseThrow( () -> new ResourceNotFoundException(
                         "L'user avec l'id " + id + " n'existe pas "));

        if (!user.getListInvitation().isEmpty())
        {
            return user.getListInvitation();
        }
        else
        {
            throw new ResourceNotFoundException("L'user " + user.getUsername() 
            + " n'a pas d'invitation de contact ");
        }
    }

    @Override
    public User refuseDemandeById(long id) {
    
        User user = securityService.getUser();


        User userHasDeleted = userRepository.findById(id)           
        .orElseThrow( () -> new ResourceNotFoundException(
            "L'user avec l'id " + id + " n'existe pas "));

        if( isContains(user, userHasDeleted))
        {
            deleteInvit(user, userHasDeleted);
        }
        
        return user;
    }

    @Override
    public List<User> getConctactsById(long id) {

        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(
                    "L'user avec l'id " + id + " n'existe pas "));

        if(user.getListContact() != null)
        {
            return user.getListContact();
        }
        else
        {
            throw new ResourceNotFoundException("L'user " + user.getUsername() 
            + " n'a pas de contact ");
        }
      
    }

    @Override
    public void deleteContactById(long idUser , long idToDelete)
    {
        
        User user = userRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException(
                    "L'user avec l'id " + idUser + " n'existe pas "));

        List<User> listContacts = new ArrayList<>();            
        
        if(user.getListContact() != null)
        {
            for(User userTest : user.getListContact())
            {
                if(!userTest.getId().equals(idToDelete))
                {
                    listContacts.add(user);
                }
            }
            user.setListContact(listContacts);

            userRepository.save(user);
            
        }
        else
        {
            throw new ResourceNotFoundException("la liste de contacte est vide ");
        }
       
  

    }

    @Override
    public void deletedContactByJwt(long id) 
    {
        User user = securityService.getUser();

        if(user.getListContact() != null)
        {

            List<User> listContacts = new ArrayList<>();            
            for(User userTest : user.getListContact())
            {
                if(!userTest.getId().equals(id))
                {
                    listContacts.add(user);
                }
            }

            user.setListContact(listContacts);

            userRepository.save(user);

        }
    }

    @Override
    public List<User> getContactsByJwt() {
    
        User user = securityService.getUser();
        if(user.getListContact() != null)
        {
            return user.getListContact();
        }
        else
        {
            throw new ResourceNotFoundException("L'user " + user.getUsername() 
            + " n'a pas de contact ");
        }
    }

    @Override
    public List<User> getSuggestContact() {
 
        List<User> list= userRepository.findAll();
        List<User> lUsers = new ArrayList<>();
        User user = securityService.getUser();

        for( User userTest : list )
        {
            
            //si l'userTest n'est pas l'user connécté 
            //si l'usertest n'est pas deja dans les contacts de l'user connecte
            //si l'userTest n'est pas dans la liste d'invitation de l'user connecte 

            if( !user.getUsername().equals(userTest.getUsername()) &&  
            !user.getListContact().contains(userTest) && 
            !userTest.getListInvitation().contains(user)
            )
            {
               lUsers.add(userTest);
            }
        }

        return lUsers;
    }

    @Override
    public List<User> getListDemande() {

        User user = securityService.getUser();

        // trouvez les user qui l'ont invité 
        List<User> lUsers = new ArrayList<>();

        List<User> lUserAll = userRepository.findAll();

        for ( User userIndex : lUserAll)
        {
            //si l'userIndex a invité l'user connected 
            if ( userIndex.getListInvitation().contains(user) )
            {
                lUsers.add(userIndex);
                System.out.println("\n userIndex " + userIndex.getUsername());
            }
        }

        return lUsers;

        
    }    




}
