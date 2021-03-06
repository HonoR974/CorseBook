package com.back.springboot.controller;

import com.back.springboot.models.User;
import com.back.springboot.service.UserService;

import java.util.Collections;
import java.util.List;

import com.back.springboot.dto.FileDTO;
import com.back.springboot.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getuserById(@PathVariable long id) {
        User user = userService.getUserById(id);

        UserDTO userDTO = userService.convertToDto(user);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/jwt/{username}")
    public ResponseEntity<UserDTO> getuserByUsername(@PathVariable String username) {
        System.out.println("\n bonne methode ");
        User user = userService.getUserByUsername(username);

        UserDTO userDTO = userService.convertToDto(user);

        System.out.println("userDTO getUserByUsername " + userDTO.toString());

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/file")
    public ResponseEntity<UserDTO> updateProfilePicture(@RequestBody FileDTO fileDTORequest) {

        User user = userService.updateProfilePicture(fileDTORequest);
        UserDTO userDTO = userService.convertToDto(user);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<User> list = userService.getAllUser();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<>(lDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all/username")
    public ResponseEntity<List<String>> getAllUserByUsername() {
        List<String> list = userService.getAllUsername();
        Collections.sort(list);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

}
