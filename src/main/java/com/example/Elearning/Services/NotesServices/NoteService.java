package com.example.Elearning.Services.NotesServices;


import com.example.Elearning.Models.NoteModels.Note;
import com.example.Elearning.Repositorys.NotesRepo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository ;

    public NoteService() {
    }

    public List<Note> findAll(Long id){
        return noteRepository.findAllByUser_id(id);
    }
    public Note findbyId(Long id){
        try {
            return noteRepository.findById(id).get();
        }catch (Exception ex){
            throw new NotFoundException("Note with id:"+id+" Notfound");
        }
    }

    public void DeleteById(Long id){
        try {
            noteRepository.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("No note with Id :"+id);
        }
    }
    public Note Save(Note note){
        return noteRepository.save(note);
    }

    public List<Note> findbyPage(Long id_page,Long id_user){
        return noteRepository.findAllByPage_idAndUser_id(id_page,id_user);
    }

}
