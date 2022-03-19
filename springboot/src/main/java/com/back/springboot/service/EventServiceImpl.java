package com.back.springboot.service;

import java.util.List;

import com.back.springboot.models.Event;
import com.back.springboot.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
    

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
      
      
        return   eventRepository.save(event);
    }

    @Override
    public List<Event> getAll() {
      

        return eventRepository.findAll();
    }

     
}
