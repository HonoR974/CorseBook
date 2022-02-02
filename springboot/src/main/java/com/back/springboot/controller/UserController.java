package com.back.springboot.controller;



import com.back.springboot.models.User;
import com.back.springboot.service.UserService;

import com.back.springboot.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    

	@Autowired
	UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getuserById(@PathVariable long id)
    {
        User user = userService.getUserById(id);

        UserDTO userDTO = userService.convertToDto(user);

        return new ResponseEntity<UserDTO>(userDTO,HttpStatus.ACCEPTED);
    }
}
