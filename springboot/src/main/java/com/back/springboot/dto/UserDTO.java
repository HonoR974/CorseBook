
package com.back.springboot.dto;

import java.util.List;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    private String email;

    List<String> listContact;

    List<String> listInvitation;

    private boolean isInvitedForContact;

    private long id_chat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getListContact() {
        return listContact;
    }

    public void setListContact(List<String> listContact) {
        this.listContact = listContact;
    }


    public List<String> getListInvitation() {
        return listInvitation;
    }

    public void setListInvitation(List<String> listInvitation) {
        this.listInvitation = listInvitation;
    }

    public boolean isInvitedForContact() {
        return isInvitedForContact;
    }

    public void setInvitedForContact(boolean isInvitedForContact) {
        this.isInvitedForContact = isInvitedForContact;
    }

    public long getId_chat() {
        return id_chat;
    }

    public void setId_chat(long id_chat) {
        this.id_chat = id_chat;
    }


    
}
