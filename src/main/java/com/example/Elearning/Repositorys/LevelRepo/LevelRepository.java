package com.example.Elearning.Repositorys.LevelRepo;

import com.example.Elearning.Models.LevelModel.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level,Long> {
    boolean existsByName(String name);

    Optional<Level> findByName(String name);
}
