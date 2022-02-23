package com.back.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatDTO {
    
    private long id;

    private List<String> listUser;

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

   

    
}
