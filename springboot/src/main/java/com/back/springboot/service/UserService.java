package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.UserDTO;
import com.back.springboot.models.User;

/**
 * Interface UserService
 */
public interface UserService {

    /**
     * Inscription de l'user
     * @param userDTO
     * @return user
     */
    User save(UserDTO userDTO);

    /**
     * Recupere un user par son username
     * @param username
     * @return user
     */
    User findByUsername(String username);


    // crud 
    List<User> getAllUser();

    User getUserById(long id);


     //convert
     UserDTO convertToDto(User user);

     User convertToEntity(UserDTO userDTO);

     List<UserDTO> convertTolistDto(List<User> lUsers);






}
