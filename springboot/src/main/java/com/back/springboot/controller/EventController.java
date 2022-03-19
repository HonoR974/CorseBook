package com.back.springboot.controller;


import java.util.List;

import com.back.springboot.models.Event;
import com.back.springboot.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/event/")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping()
    public ResponseEntity<Event> createEvent(@RequestBody Event event)
    {
        event = eventService.createEvent(event);

        return new ResponseEntity<>(event, HttpStatus.ACCEPTED);
    }
    
    @GetMapping()
    public ResponseEntity<List<Event>> getAll()
    {
        List<Event> list = eventService.getAll();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
