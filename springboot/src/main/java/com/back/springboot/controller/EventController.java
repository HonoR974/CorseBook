package com.back.springboot.controller;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.back.springboot.dto.EventDTO;
import com.back.springboot.models.Event;
import com.back.springboot.service.EventService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/event/")
public class EventController {

    @Autowired
    private EventService eventService;

    //get By User id_user 
    @GetMapping("user/{id}")
    public ResponseEntity<List<EventDTO>> getEventByUserID(@PathVariable long id)
    {
        List<Event> lEvents = eventService.getEventByUserID(id);
        List<EventDTO> lDtos  = eventService.convertListEntity(lEvents);

        return new ResponseEntity<>(lDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("user")
    public ResponseEntity<List<EventDTO>> getEventByUser()
    {
        List<Event> lEvents = eventService.getEventByUser();
        List<EventDTO> lDtos  = eventService.convertListEntity(lEvents);

        return new ResponseEntity<>(lDtos, HttpStatus.ACCEPTED);
    }

    //Add User on Event id-event 
    @PostMapping("add/user/{id}")
    public ResponseEntity<EventDTO> addUserOnEvent(@PathVariable long id )
    {
        Event event = eventService.addUserOnEvent(id);
        EventDTO eventDTO = eventService.convertEntity(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.ACCEPTED);
    }

    //delete User on Event id-event
    @PostMapping("delete/user/{id}")
    public ResponseEntity<EventDTO> deleteUserOnEvent(@PathVariable long id)
    {
        Event event = eventService.deleteUserOnEvent(id);
    
        EventDTO eventDTO = eventService.convertEntity(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.ACCEPTED);
    }



    //------------- CRUD 
    @PostMapping()
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTORequest )
    {
      
       
       Event  event = eventService.createEvent(eventService.convertDTO(eventDTORequest));

       EventDTO eventDTO = eventService.convertEntity(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.ACCEPTED);
    }
    
    @GetMapping()
    public ResponseEntity<List<EventDTO>> getAll()
    {
        List<Event> list = eventService.getAll().stream()
        .sorted(Comparator.comparing(Event::getDateCreate).reversed())
        .collect(Collectors.toList());

        List<EventDTO> lDtos = eventService.convertListEntity(list);
        return new ResponseEntity<>(lDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable long id)
    {
        Event event = eventService.getEventById(id);

        EventDTO eventDTO = eventService.convertEntity(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity< Map<String, Boolean>> deleteEventById(@PathVariable long id )
    {
        eventService.deleteById(id);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("all")
    public ResponseEntity< Map<String, Boolean>> deleteEventAll( )
    {
        eventService.deleteAll();

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //--------------------- CRUD 
}
