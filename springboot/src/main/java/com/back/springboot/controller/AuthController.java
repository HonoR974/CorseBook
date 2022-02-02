package com.back.springboot.controller;




import javax.servlet.http.HttpServletRequest;

import com.back.springboot.models.JwtRequest;
import com.back.springboot.models.JwtResponse;
import com.back.springboot.models.User;
import com.back.springboot.service.UserService;
import com.back.springboot.configuration.JwtTokenUtil;
import com.back.springboot.configuration.JwtUserDetailsService;
import com.back.springboot.dto.UserDTO;

import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

    @Autowired
    private JwtUserDetailsService jwtUserDetail;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


	   /**
     * Inscription de l'user
     * @param user
     * @param request
     * @param errors
     * @return user
     * @throws Exception
     */
    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody UserDTO user,
                                      HttpServletRequest request,
                                      Errors errors) throws Exception
    {

        User registered = userService.save(user);

        return new ResponseEntity<User>(registered, HttpStatus.ACCEPTED);
    }

    /**
     * Authentifie l'user
     * @param authenticationRequest
     * @return jwt
     * @throws Exception
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
    {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetail
                .loadUserByUsername(authenticationRequest.getUsername());

        
        final String token = jwtTokenUtil.generateToken(userDetails);

        User user = userService.findByUsername(userDetails.getUsername());
        JwtResponse jwtResponse = new JwtResponse(token, user.getUsername(), user.getEmail());
     
        return ResponseEntity.ok(jwtResponse);
    }


    /**
     * Verfie l'authentification
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        System.out.println("\n \n "+ username+ " \n "  + password );
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    
    
    /**
     * Recupere un user par le jwt
     * @param jwt
     * @return user
     */
    @RequestMapping(value = "/token/{jwt}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByToken( @PathVariable String jwt)
    {

        String username= jwtTokenUtil.getUsernameFromToken(jwt);

        final UserDetails userDetails = jwtUserDetail.loadUserByUsername(username);

        User user = userService.findByUsername(userDetails.getUsername());

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

   


}