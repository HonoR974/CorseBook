package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.dto.ChatDTO;
import com.back.springboot.models.Chat;
import com.back.springboot.service.ChatService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/chat/")
public class ChatController {

    @Autowired
    private ChatService chatService;

    

    @GetMapping()
    public ResponseEntity<List<ChatDTO>> getAllChat()
    {
        List<Chat> list = chatService.getAll();

        List<ChatDTO> lChatDTOs = chatService.convertToDtoList(list);

        return new ResponseEntity<>(lChatDTOs, HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ChatDTO> getById(@PathVariable long id)
    {
        Chat chat = chatService.getById(id);

        ChatDTO chatDTO = chatService.convertToDTO(chat);

        return new ResponseEntity<ChatDTO>(chatDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id)
    {
        String result =  chatService.deleteById(id);

        return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("all")
    public ResponseEntity< List<ChatDTO> > deleteAll()
    {
 
        chatService.deleteAll();

        List<Chat> list = chatService.getAll();

        List<ChatDTO> lChatDTOs = chatService.convertToDtoList(list);

        return new ResponseEntity<>(lChatDTOs, HttpStatus.ACCEPTED);
    }
    
 
}
