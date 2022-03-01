package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.ChatDTO;
import com.back.springboot.models.Chat;
import com.back.springboot.models.Message;

public interface ChatService {
    
    //crud 

    List<Chat> getAll();

    Chat getById(long id);

    String deleteById(long id);


    //convert 

    ChatDTO convertToDTO(Chat chat);

    List<ChatDTO> convertToDtoList(List<Chat> chats );

    // 
    List<Message> getMessagesByIdChat(long id);

    //delete all chats 
    void deleteAll();
}
