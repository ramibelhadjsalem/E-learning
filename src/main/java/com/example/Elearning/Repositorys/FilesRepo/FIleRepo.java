package com.example.Elearning.Repositorys.FilesRepo;

import com.example.Elearning.Models.FilesModules.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FIleRepo extends JpaRepository<File,Long> {
}
