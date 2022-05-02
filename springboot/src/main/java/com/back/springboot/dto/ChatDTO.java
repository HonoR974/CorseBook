package com.back.springboot.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ChatDTO {

    private long id;

    private String name;

    private List<String> listUser;

    private HashMap<String, String> listMessage;

    public ChatDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getListUser() {
        return listUser;
    }

    public void setListUser(List<String> listUser) {
        this.listUser = listUser;
    }

    public HashMap<String, String> getListMessage() {
        return listMessage;
    }

    public void setListMessage(HashMap<String, String> listMessage) {
        this.listMessage = listMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
