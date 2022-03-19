package com.back.springboot.service;

import java.util.List;

import com.back.springboot.models.Event;

public interface EventService {
    
    List<Event> getAll();

    Event createEvent(Event event);
}
