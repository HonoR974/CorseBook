package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.EventDTO;
import com.back.springboot.models.Event;

public interface EventService {
    

    //add user 
    Event addUserOnEvent(long id);

    //crud 

    List<Event> getAll();

    Event getEventById(long id);

    Event createEvent(Event event);

    void deleteById(long id);

    void deleteAll();
    //convert 
    Event convertDTO(EventDTO EventDTO);

    EventDTO convertEntity(Event event);

    List<EventDTO> convertListEntity(List<Event> list);
}
