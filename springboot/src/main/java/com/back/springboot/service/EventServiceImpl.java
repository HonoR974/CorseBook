package com.back.springboot.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.back.springboot.dto.EventDTO;
import com.back.springboot.dto.FileDTO;
import com.back.springboot.dto.MarkerDTO;
import com.back.springboot.models.Event;
import com.back.springboot.models.File;
import com.back.springboot.models.Marker;
import com.back.springboot.repository.EventRepository;
import com.back.springboot.repository.FileRepository;
import com.back.springboot.repository.MarkerRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MarkerRepository markerRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Event createEvent(Event event) {


        System.out.println("\n \n create " + event.toString());
       
        if(event.getListMarkers() != null)
        {
           
            for(Marker mark : event.getListMarkers())
            {
                System.out.println("\n marker " + mark.getEvent().getId());
                markerRepository.save(mark);
            }
        }
        if( event.getListFile() != null)
        {
            fileRepository.saveAll(event.getListFile());
            System.out.println("\n  file ");
        }
        eventRepository.save(event);
        
        return   event;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }


    //--------------CONVERT 
     
    @Override
    public Event convertDTO(EventDTO eventDTO) {
        Event event = modelMapper.map((eventDTO), Event.class);

        //location
        if(eventDTO.getListMarker() != null)
        {
            List<Marker> listMarkers = new ArrayList();
            
            for(MarkerDTO locationDTO : eventDTO.getListMarker() )
            {
                Marker location = new Marker(locationDTO.getLatitude(), locationDTO.getLongitude());
                location.setEvent(event);
                listMarkers.add(location);
            }
            event.setListMarkers(listMarkers);
           
        }

       if(eventDTO.getListFileAPI() != null)
       {
           List<File> list = new ArrayList();
           
           for( FileDTO fileDTO : eventDTO.getListFileAPI())
           {
               File file = new File(fileDTO.getUrl(), fileDTO.getName());
                file.setEvent(event);
                list.add(file);
           }
           event.setListFile(list);
       }
        
        return event;
    }

    @Override
    public EventDTO convertEntity(Event event) {
       
        EventDTO eventDTO = modelMapper.map(event, EventDTO.class);

        if( event.getListMarkers() != null)
        {
            List<MarkerDTO> lDtos = new ArrayList<>();

            for ( Marker marker : event.getListMarkers())
            {
                MarkerDTO markerDTO = new MarkerDTO(marker.getLatitude(), marker.getLongitude());
                lDtos.add(markerDTO);

            }
            eventDTO.setListMarker(lDtos);
        }
        return eventDTO;
    }

	@Override
	public List<EventDTO> convertListEntity(List<Event> list) {
	
        List<EventDTO> listDtos = new ArrayList<>();

        for(Event event : list)
        {
            listDtos.add(convertEntity(event));
        }
		return listDtos;
	}
}
