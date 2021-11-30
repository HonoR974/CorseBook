package com.back.springboot.service;

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
}
