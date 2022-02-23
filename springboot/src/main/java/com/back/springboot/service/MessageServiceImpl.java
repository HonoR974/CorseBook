package com.back.springboot.service;

import java.util.List;

import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Chat;
import com.back.springboot.models.Message;
import com.back.springboot.repository.ChatRepository;
import com.back.springboot.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> saveAllByIdChat(long id, List<Message> list) {
        
        Chat chat = chatRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("le chat avec l'id : "+
         id + " n'existe pas "));

         chat.setMessages(list);

         for(Message message : list)
         {
             message.setChat(chat);
         }

         messageRepository.saveAll(list);
         chatRepository.save(chat);
        return list;
    }
    
    
}
