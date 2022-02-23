package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.dto.ChatDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Chat;
import com.back.springboot.models.Message;
import com.back.springboot.models.User;
import com.back.springboot.repository.ChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    //------------  CRUD ---------------//

    @Override
    public List<Chat> getAll() {
        // TODO Auto-generated method stub
        return chatRepository.findAll();
    }


    @Override
    public Chat getById(long id) {
        // TODO Auto-generated method stub
        return chatRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("le chat avec l'id : "+
         id + " n'existe pas "));

    }

    @Override
    public String deleteById(long id) {
       
        Chat chat = chatRepository.findById(id)
                    .orElseThrow( () -> new ResourceNotFoundException("le chat avec l'id : "+ id + " n'existe pas "));
        
        chatRepository.delete(chat);   

        return "deleted";
    }



    //---------------   convert ----------------//
 
    
    @Override
    public ChatDTO convertToDTO(Chat chat) {
       
        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setId(chat.getId());
    
        List<String> listUser = new ArrayList<>();

        for ( User user : chat.getUsers())
        {
            listUser.add(user.getUsername());
        }

        chatDTO.setListUser(listUser);


        return chatDTO;
    }

    @Override
    public List<ChatDTO> convertToDtoList(List<Chat> chats) {

        List<ChatDTO> lDtos = new ArrayList();
        for( Chat chat : chats)
        {
            lDtos.add(convertToDTO(chat));
        }

        return lDtos;
    }




   //-------------

   @Override
   public List<Message> getMessagesByIdChat(long id) {
      
        Chat chat = chatRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("le chat avec l'id : "+ id + " n'existe pas "));

       return chat.getMessages();
   }
}
