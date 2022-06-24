package com.example.Elearning.Controllers;

import com.example.Elearning.Models.Subjects.Subject;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Services.LevelServices.LevelService;
import com.example.Elearning.Services.SubjectServices.SectionService;
import com.example.Elearning.Services.SubjectServices.SubjectService;
import com.example.Elearning.Services.Userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  SubjectService subjectService;
  @Autowired
  SectionService sectionService;
  @Autowired
  UserService userService;
  @Autowired
  LevelService levelService;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  @GetMapping("/subject")
  public List<Subject> getallSubject() {
    return subjectService.getAllSubjects();
  }

  @GetMapping("/subject/{id}")
  public Subject getallSubject(@PathVariable Long id) {
    return subjectService.findById(id);
  }

  @DeleteMapping("/subject/{id}")
  public void deletesubjectbyid(@PathVariable Long id) {
    subjectService.deleteByid(id);
  }







  /*
  -------------------------------------------test user------------------------------
  */
  @GetMapping("/getAllUser")
  public List<User> findallUser() {
    return userService.findAll();
  }
/*@PostMapping("/userid/{user_id}/levelid/{level_id}")
  public User add
}*/

}

