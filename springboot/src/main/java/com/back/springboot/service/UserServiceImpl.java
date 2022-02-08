package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.dto.UserDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.User;
import com.back.springboot.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Inscription de l'user
     * @param userDTO
     * @return user
     */
    @Override
    public User save(UserDTO userDTO) {

        

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setMatchingPassword(user.getPassword());
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    /**
     * Recupere un user par son username
     * @param username
     * @return user
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                        .orElseThrow( () -> new ResourceNotFoundException("cette usernerame ne correspond a aucun user "));
    }



    
    @Override
    public List<User> getAllUser() {


        return userRepository.findAll();
    }


    @Override
    public User getUserById(long id)
    {

        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with id " + id +  " does'nt exist "));

        return user;
    }


    //------------Convert ---------------//
    @Override
    public UserDTO convertToDto(User user) {
        
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        //listContact -> String 
        if(user.getListContact() !=  null)
        {
            List<String> listName = new ArrayList<>();
            for(User userTest : user.getListContact())
            {
                listName.add(userTest.getUsername());
            }
            userDTO.setListContact(listName);
        }

        //listeInvitationContact -> String 
        if(user.getlInvitationContact() !=  null)
        {
            List<String> listName = new ArrayList<>();
            for(User userTest : user.getlInvitationContact())
            {
                listName.add(userTest.getUsername());
            }
            userDTO.setListInvitation(listName);
        }

        //is invited by current user 
        User currentUser = securityService.getUser();


        //si l'user  est connécté 
        //et que sa liste de contacte ne contient pas l'user a convertire 
        if(currentUser != null && !currentUser.getListContact().contains(user) )
        {
           if( currentUser.getlInvitationContact().contains(user))
           {
               userDTO.setInvitedForContact(true);
           }
           else 
           {
            userDTO.setInvitedForContact(false);
           }
        }

        return userDTO;
    }



    @Override
    public User convertToEntity(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> convertTolistDto(List<User> lUsers) {
       List<UserDTO> list = new ArrayList<>();

       for(User user : lUsers)
       {
           list.add(convertToDto(user));
       }
        return list;
    }

    
}
