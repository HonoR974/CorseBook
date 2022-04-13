package com.back.springboot.service;


import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.User;
import com.back.springboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service SecurityServiceimpl
 */
@Service
public class SecurityServiceImpl implements SecurityService{


    @Autowired
    private UserRepository userRepository;

    /**
     * Verification de l'authentification
     * @return boolean
     */
    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    /**
     * Recupere l'user
     * @return user
     */
    @Override
    public User getUser()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
        .orElseThrow( () -> new ResourceNotFoundException("cette usernerame " + username+  " ne correspond a aucun user "));
        
        
        return user;
    }

    /**
     * Recupere l'username
     * @return string
     */
    @Override
    public String getUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }



}
