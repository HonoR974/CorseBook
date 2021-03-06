package com.back.springboot.controller;

import com.back.springboot.service.ContactService;
import com.back.springboot.service.UserService;

import java.util.List;

import com.back.springboot.models.User;
import com.back.springboot.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/contact/")
public class ContactController {

    private final ContactService contactService;

    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUser() {
        List<User> lUsers = userService.getAllUser();

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return ResponseEntity.ok(lDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {

        User user = userService.getUserById(id);

        UserDTO userDTO = userService.convertToDto(user);

        return ResponseEntity.ok(userDTO);
    }

    // envoie une demande d'ajout a un user par son id
    // renvoie l'user ayant recu l'invitation
    @PostMapping("{id}")
    public ResponseEntity<UserDTO> askForAddContactById(@PathVariable long id) {

        User userRecevant = contactService.setAskContact(id);

        UserDTO userDTO = userService.convertToDto(userRecevant);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }

    // accepte la demande d'ajout recu
    // l'user conn??ct?? avec le jwt accepte l'user a l'id envoy??
    // renvoie les 2 Users
    @PostMapping("/accept/{id}")
    public ResponseEntity<List<UserDTO>> accepteDemandeByID(@PathVariable long id) {

        List<User> list = contactService.accepteDemande(id);

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);

    }

    // refuse la demande d'ajout recu
    // refuse la demande de l'user correspondant ?? l'id envoy??
    @PostMapping("/refuse/{id}")
    public ResponseEntity<UserDTO> refuseDemandeById(@PathVariable long id) {
        User user = contactService.refuseDemandeById(id);

        UserDTO userDTO = userService.convertToDto(user);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
    }

    // affiche la list des invitations par le jwt
    @GetMapping("/invitations")
    public ResponseEntity<List<UserDTO>> getInvitationByJwt() {
        List<User> list = contactService.getInvitation();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // affiche la list des invitations par l'id de l'user
    @GetMapping("/invitations/{id}")
    public ResponseEntity<List<UserDTO>> getInvitationById(@PathVariable long id) {
        List<User> list = contactService.getInvitationById(id);

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // affiche la list de contacte par l'id
    @GetMapping("/list/{id}")
    public ResponseEntity<List<UserDTO>> getContactsById(@PathVariable long id) {

        List<User> lUsers = contactService.getConctactsById(id);

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);

    }

    // affiche la list de contacte par jwt

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getContatcsByJwt() {

        List<User> lUsers = contactService.getContactsByJwt();

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // supprime un contact
    // id user qui possede la liste de contact
    // idToDeleted user de la liste de contact
    @DeleteMapping("/list/{id}/delete/{idToDelete}")
    public ResponseEntity<String> deleteContactByID(@PathVariable long id,
            @PathVariable long idToDelete) {
        contactService.deleteContactById(id, idToDelete);

        return new ResponseEntity<String>("Accepted", HttpStatus.ACCEPTED);
    }

    // supprime un contact by jwt
    // id user de la liste de contact
    // return les contacts de l'user au jwt
    @DeleteMapping("list/delete/{id}")
    public ResponseEntity<List<UserDTO>> deleteContactByJwt(@PathVariable long id) {
        contactService.deletedContactByJwt(id);

        List<User> lUsers = contactService.getContactsByJwt();

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // get Suggest Contact
    @GetMapping("suggest")
    public ResponseEntity<?> getSuggestContact() {
        List<User> list = contactService.getSuggestContact();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // get List des invitation envoy?? par l'user
    // donc ses demandes
    @GetMapping("demandes")
    public ResponseEntity<List<UserDTO>> getListInvitationByJwt() {
        List<User> list = contactService.getListDemande();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // supprime une demande envoy?? par l'user
    @PostMapping("demandes/{id}")
    public ResponseEntity<List<UserDTO>> cancelDemande(@PathVariable long id) {

        contactService.cancelDemande(id);
        List<User> list = contactService.getListDemande();

        List<UserDTO> lDtos = userService.convertTolistDto(list);

        return new ResponseEntity<List<UserDTO>>(lDtos, HttpStatus.ACCEPTED);
    }

    // supprime tout les contacts et invitations de chaque user
    // return tous les users
    @DeleteMapping("clean")
    public ResponseEntity<List<UserDTO>> cleanContact() {
        contactService.cleanAll();

        List<User> lUsers = userService.getAllUser();

        List<UserDTO> lDtos = userService.convertTolistDto(lUsers);

        return ResponseEntity.ok(lDtos);
    }
}
