package com.back.springboot.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.back.springboot.dto.EventDTO;
import com.back.springboot.models.Event;
import com.back.springboot.service.EventService;

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

    

    //Add User on Event
    @PostMapping("add/{id}")
    public ResponseEntity<EventDTO> addUserOnEvent(@PathVariable long id )
    {
        Event event = eventService.addUserOnEvent(id);
        EventDTO eventDTO = eventService.convertEntity(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.ACCEPTED);
    }


    //------------- CRUD 
    @PostMapping()
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTORequest )
    {
      
        System.out.println(("\n request " + eventDTORequest.toString()));
       Event  event = eventService.createEvent(eventService.convertDTO(eventDTORequest));

       EventDTO eventDTO = eventService.convertEntity(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.ACCEPTED);
    }
    
    @GetMapping()
    public ResponseEntity<List<EventDTO>> getAll()
    {
        List<Event> list = eventService.getAll();

        List<EventDTO> lDtos = eventService.convertListEntity(list);
        return new ResponseEntity<>(lDtos, HttpStatus.ACCEPTED);
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
