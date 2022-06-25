package com.example.Elearning.Repositorys.ChapitresRepo;

import com.example.Elearning.Models.ChapitresModels.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre,Long> {
    Optional<Chapitre> findByName(String name);
}
