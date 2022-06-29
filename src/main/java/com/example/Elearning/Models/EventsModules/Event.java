package com.example.Elearning.Models.EventsModules;

import com.example.Elearning.Models.UserModel.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name ;
    private String description;
    private Float price;

    private Date DateOfStart;
    private String videoUrl;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "eventSource_id")
    private User user;




}
