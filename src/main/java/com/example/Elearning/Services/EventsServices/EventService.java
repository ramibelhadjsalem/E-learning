package com.example.Elearning.Services.EventsServices;


import com.example.Elearning.Models.EventsModules.Event;
import com.example.Elearning.Repositorys.EventsRepo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired private EventRepository eventRepository;

    public EventService() {
    }

    public List<Event> findAll(){
        return eventRepository.findAll(Sort.by("dateOfStart").descending());
    }
    public  Event findById(Long id){
        return eventRepository.findById(id).get();
    }
    public Event Save(Event event){
        return eventRepository.save(event);
    }
    public void deleteByid(Long id){
        eventRepository.deleteById(id);
    }

}
