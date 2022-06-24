package com.example.Elearning.Repositorys.SubjectRepo;

import com.example.Elearning.Models.SectionModels.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section,Long> {
    boolean existsByName(String name);

    Optional<Section> findByName(String name);
}
