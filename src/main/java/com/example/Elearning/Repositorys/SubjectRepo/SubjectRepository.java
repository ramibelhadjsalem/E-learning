package com.example.Elearning.Repositorys.SubjectRepo;

import com.example.Elearning.Models.Subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findByName(String name);


    boolean existsByName(String name);
}
