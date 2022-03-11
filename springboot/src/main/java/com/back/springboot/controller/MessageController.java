package com.back.springboot.controller;

import java.util.List;

import com.back.springboot.service.ChatService;
import com.back.springboot.service.MessageService;
import com.back.springboot.models.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/message/")
public class MessageController {
    
    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    //Get Message By Id Chat 
    @GetMapping("chat/{id}")
    public ResponseEntity<List<Message>> getMessageByIdChat(@PathVariable long id)
    {
        List<Message> messages = chatService.getMessagesByIdChat(id);

        return new ResponseEntity<>(messages, HttpStatus.ACCEPTED);
    }




    //save all message by id chat 
    @PostMapping("chat/{id}")
    public ResponseEntity<List<Message>> saveAllMessage(@PathVariable long id,
                                            @RequestBody List<Message> list)
    {

        System.out.println("\n list " + list.size());
        List<Message> listMessage = messageService.saveAllByIdChat(id, list);

        return new ResponseEntity<>(listMessage, HttpStatus.ACCEPTED);
    }
}
