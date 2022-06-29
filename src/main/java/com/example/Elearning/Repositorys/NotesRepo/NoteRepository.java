package com.example.Elearning.Repositorys.NotesRepo;

import com.example.Elearning.Models.NoteModels.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {


    @Query("select n from Note n where n.user_id = ?1")
    List<Note> findAllByUser_id(Long id);



    @Query("select n from Note n where n.page.id = ?1 and n.user_id = ?2")
    List<Note> findAllByPage_idAndUser_id(Long id_page, Long user_id);
}
