package com.back.springboot.service;

import java.util.List;

import com.back.springboot.models.Message;

public interface MessageService {
    
    List<Message> saveAllByIdChat(long id, List<Message> list);
}
