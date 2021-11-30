package com.back.springboot.service;

import com.back.springboot.dto.UserDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.User;
import com.back.springboot.repository.UserRepository;

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
}
