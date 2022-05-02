package com.back.springboot.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.back.springboot.dto.CommentDTO;
import com.back.springboot.dto.EventDTO;
import com.back.springboot.dto.FileDTO;
import com.back.springboot.dto.MarkerDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Chat;
import com.back.springboot.models.Comment;
import com.back.springboot.models.Event;
import com.back.springboot.models.File;
import com.back.springboot.models.Marker;
import com.back.springboot.models.User;
import com.back.springboot.repository.ChatRepository;
import com.back.springboot.repository.CommentRepository;
import com.back.springboot.repository.EventRepository;
import com.back.springboot.repository.FileRepository;
import com.back.springboot.repository.MarkerRepository;
import com.back.springboot.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;

    private final EventRepository eventRepository;

    private final MarkerRepository markerRepository;

    private final FileRepository fileRepository;

    private final SecurityService securityService;

    private final UserRepository userRepository;

    private final ChatRepository chatRepository;

    private final CommentRepository commentRepository;

    public EventServiceImpl(ModelMapper modelMapper, EventRepository eventRepository, MarkerRepository markerRepository,
            FileRepository fileRepository, SecurityService securityService, UserRepository userRepository,
            ChatRepository chatRepository, CommentRepository commentRepository) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.markerRepository = markerRepository;
        this.fileRepository = fileRepository;
        this.securityService = securityService;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Event addUserOnEvent(long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event not extist with id : " + id));

        User user = securityService.getUser();

        List<User> list = new ArrayList<>();
        list.addAll(event.getListUser());
        list.add(user);

        event.setListUser(list);
        eventRepository.save(event);

        List<Event> lEvents = new ArrayList<>();
        lEvents.addAll(user.getListEvent());
        lEvents.add(event);
        user.setListEvent(lEvents);
        userRepository.save(user);

        return event;
    }

    @Override
    public Event deleteUserOnEvent(long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event not extist with id : " + id));

        User user = securityService.getUser();

        List<User> lUsers = event.getListUser();
        lUsers.remove(user);
        event.setListUser(lUsers);

        List<Event> lEvents = user.getListEvent();
        lEvents.remove(event);
        user.setListEvent(lEvents);

        eventRepository.save(event);
        userRepository.save(user);

        return event;

    }

    @Override
    public Event createEvent(Event event) {

        System.out.println("\n create event method " + event);

        eventRepository.save(event);
        // marker
        if (event.getListMarkers() != null) {
            List<Marker> lMarkers = new ArrayList<>();

            for (Marker mark : event.getListMarkers()) {

                lMarkers.add(mark);
                mark.setEvent(event);
                markerRepository.save(mark);
            }
            event.setListMarkers(lMarkers);
        }

        // file
        if (event.getListFile() != null) {
            List<File> lFiles = new ArrayList<>();

            for (File file : event.getListFile()) {
                lFiles.add(file);
                file.setEvent(event);
                fileRepository.save(file);
            }
            event.setListFile(lFiles);
        }

        // date yyyy-MM-dd to dd-MM-yyyy
        try {

            // Setting the pattern
            String sDebut = new SimpleDateFormat("dd-MM-yyyy").format(event.getDateDebut());
            String sFin = new SimpleDateFormat("dd-MM-yyyy").format(event.getDateFin());

            Date dateDebut = new SimpleDateFormat("dd-MM-yyyy").parse(sDebut);
            Date dateFin = new SimpleDateFormat("dd-MM-yyyy").parse(sFin);

            event.setDateDebut(dateDebut);
            event.setDateFin(dateFin);

            User user = securityService.getUser();
            event.setNameCreator(user.getUsername());
            event.setDateCreate(new Date());

            addChat(event);
            addUser(event);

        } catch (ParseException e) {

            System.out.println("\n bad try ");
            e.printStackTrace();
        }

        return eventRepository.save(event);
    }

    public void addChat(Event event) {

        Chat chat = new Chat();
        chat.setName(event.getName());
        chat.setEvent(event);
        event.setChat(chat);

        chatRepository.save(chat);
        eventRepository.save(event);

    }

    public void addUser(Event event) {

        // user & event
        User user = securityService.getUser();
        List<User> list = new ArrayList<>();
        list.add(user);
        event.setListUser(list);

        List<Event> lEvents = new ArrayList<>();
        lEvents.add(event);
        user.setListEvent(lEvents);

        // chat & user
        Chat chat = event.getChat();
        chat.setUsers(list);

        List<Chat> lChats = new ArrayList<>();
        lChats.add(chat);
        user.setChats(lChats);

        chatRepository.save(chat);
        eventRepository.save(event);
        userRepository.save(user);
    }

    @Override
    public List<Event> getAll() {
        System.out.println("\n get all ");
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event not extist with id : " + id));

        return event;
    }

    @Override
    public void deleteById(long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event not extist with id : " + id));

        if (event.getListFile() != null) {
            fileRepository.deleteAll(event.getListFile());
        }
        if (event.getListMarkers() != null) {
            markerRepository.deleteAll(event.getListMarkers());
        }

        // liste user
        if (event.getListUser() != null) {
            List<Event> listEvent = new ArrayList<>();
            for (User user : event.getListUser()) {
                listEvent = user.getListEvent();
                listEvent.remove(event);
                user.setListEvent(listEvent);
                userRepository.save(user);
            }

        }

        eventRepository.delete(event);
    }

    @Override
    public void deleteAll() {
        markerRepository.deleteAll();

        List<Event> list = eventRepository.findAll();

        for (Event event : list) {
            // file
            if (event.getListFile() != null) {
                fileRepository.deleteAll(event.getListFile());
            }

            // liste user
            if (event.getListUser() != null) {
                List<Event> listEvent = new ArrayList<>();
                for (User user : event.getListUser()) {
                    listEvent = user.getListEvent();
                    listEvent.remove(event);
                    user.setListEvent(listEvent);
                    userRepository.save(user);
                }

            }

            // comment
            if (event.getListComments() != null) {
                commentRepository.deleteAll(event.getListComments());
            }
        }

        eventRepository.deleteAll();
    }

    // --------------CONVERT

    public List<CommentDTO> getCommentsDTOByPublication(Event event) {
        List<CommentDTO> list = new ArrayList<>();

        for (Comment com : event.getListComments()) {
            CommentDTO commentDTO = modelMapper.map(com, CommentDTO.class);
            commentDTO.setId_event(event.getId());
            commentDTO.setUsername(com.getUser().getUsername());

            list.add(commentDTO);
        }
        return list;
    }

    @Override
    public Event convertDTO(EventDTO eventDTO) {
        Event event = modelMapper.map((eventDTO), Event.class);

        // location
        if (eventDTO.getListMarker() != null) {
            List<Marker> listMarkers = new ArrayList();

            for (MarkerDTO locationDTO : eventDTO.getListMarker()) {
                Marker location = new Marker(locationDTO.getLatitude(), locationDTO.getLongitude());
                location.setEvent(event);
                listMarkers.add(location);
            }
            event.setListMarkers(listMarkers);

        }

        if (eventDTO.getListFileAPI() != null) {
            List<File> list = new ArrayList();

            for (FileDTO fileDTO : eventDTO.getListFileAPI()) {
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

        // marker
        if (event.getListMarkers() != null) {
            List<MarkerDTO> lDtos = new ArrayList<>();

            for (Marker marker : event.getListMarkers()) {
                MarkerDTO markerDTO = new MarkerDTO(marker.getLatitude(), marker.getLongitude());
                lDtos.add(markerDTO);

            }
            eventDTO.setListMarker(lDtos);
        }

        // file
        if (event.getListFile() != null) {
            List<FileDTO> lFileDTOs = new ArrayList<>();

            for (File file : event.getListFile()) {
                FileDTO fileDTO = new FileDTO(file.getUrl(), file.getName());
                lFileDTOs.add(fileDTO);
            }
            eventDTO.setListFileAPI(lFileDTOs);

        }

        // user particpant & current user is participed ?
        if (event.getListUser() != null) {

            List<String> lUsers = new ArrayList<>();
            User userCurrent = securityService.getUser();

            for (User user : event.getListUser()) {
                lUsers.add(user.getUsername());

                if (userCurrent.getUsername().equalsIgnoreCase(user.getUsername())) {
                    eventDTO.setParticiped(true);
                }
            }
            eventDTO.setListParticipant(lUsers);
        }

        // comment
        if (event.getListComments() != null) {
            eventDTO.setListComments(getCommentsDTOByPublication(event));
        }

        // date
        // Date to string
        eventDTO.setDateDebut(new SimpleDateFormat("dd-MM-yyyy").format(event.getDateDebut()));
        eventDTO.setDateFin(new SimpleDateFormat("dd-MM-yyyy").format(event.getDateFin()));

        if (event.getChat() != null) {

            eventDTO.setId_chat(event.getChat().getId());
        }

        return eventDTO;
    }

    @Override
    public List<EventDTO> convertListEntity(List<Event> list) {

        List<EventDTO> listDtos = new ArrayList<>();

        for (Event event : list) {
            listDtos.add(convertEntity(event));
        }
        return listDtos;
    }

    @Override
    public List<Event> getEventByUserID(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user not extist with id : " + id));
        List<Event> lEvents = new ArrayList<>();

        if (user.getListEvent() != null) {
            lEvents.addAll(user.getListEvent());
        }
        return lEvents;
    }

    @Override
    public List<Event> getEventByUser() {

        User user = securityService.getUser();
        List<Event> lEvents = new ArrayList<>();

        if (user.getListEvent() != null) {
            lEvents.addAll(user.getListEvent());

            System.out.println("syze lEvent " + lEvents.size());
        }

        return lEvents;
    }

}
