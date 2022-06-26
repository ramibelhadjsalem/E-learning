package com.example.Elearning.DTOs.Request;

import net.bytebuddy.utility.nullability.MaybeNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NoteDto {

    private String myNote ;

    @NotNull(message = "id page is required")
    @Min(value = 0,message = "id page must be a number")
    private Long id_page;

    public NoteDto() {
    }

    public NoteDto(String myNote,Long id_page) {
        this.myNote = myNote;

        this.id_page = id_page;
    }

    public String getMyNote() {
        return myNote;
    }

    public void setMyNote(String myNote) {
        this.myNote = myNote;
    }



    public Long getId_page() {
        return id_page;
    }

    public void setId_page(Long id_page) {
        this.id_page = id_page;
    }
}
