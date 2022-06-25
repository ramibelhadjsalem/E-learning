package com.example.Elearning.Repositorys.PagesModels;

import com.example.Elearning.Models.PagesModels.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PageRepository extends JpaRepository<Page,Long> {
    boolean existsByName(String name);
    Optional<Page> findByName(String name);
}
