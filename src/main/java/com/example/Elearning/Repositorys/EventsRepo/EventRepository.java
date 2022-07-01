package com.example.Elearning.Repositorys.EventsRepo;

import com.example.Elearning.Models.EventsModules.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {


}
