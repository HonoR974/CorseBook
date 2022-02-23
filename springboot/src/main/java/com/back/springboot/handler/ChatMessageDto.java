package com.back.springboot.handler;

public class ChatMessageDto {

    private String username;
    private String message;

    
    public ChatMessageDto() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
