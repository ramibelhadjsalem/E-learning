package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.PageDTO;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.ChapitresModels.Chapitre;
import com.example.Elearning.Models.PagesModels.Page;
import com.example.Elearning.Services.ChapitresServices.ChapitreService;
import com.example.Elearning.Services.PagesServices.PageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/page")
public class PageController {
    @Autowired
    private PageService pageService;
    @Autowired
    private ChapitreService chapitreService;
    @Autowired
    ModelMapper modelMapper;

    public PageController() {
    }

    @GetMapping
    @JsonView(View.page.class)

    public List<Page> findAll(){
        return pageService.findAll();
    }
    @GetMapping("/{id}")
    @JsonView(View.base.class)
    public ResponseEntity<?> findById(@PathVariable Long id){
        return  ResponseEntity.ok(pageService.findById(id));
    }
    @GetMapping("/pagename/{name}")
    @JsonView(View.base.class)
    public ResponseEntity<?> findById(@PathVariable String name){
        return  ResponseEntity.ok(pageService.findByName(name));
    }
    @PostMapping
    @JsonView(View.base.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> SavePage(@Valid @RequestBody PageDTO pageDTO){
        Page page= modelMapper.map(pageDTO ,Page.class);
        Chapitre chapitre = chapitreService.findById(pageDTO.getChapitre_id());

        page.setPageNumber(chapitre.getTotalPages()+1);
        chapitre.setTotalPages(chapitre.getTotalPages()+1);
        page.setChapitre(chapitre);

        chapitre.getPages().add(page);

        return new ResponseEntity<>(pageService.Save(page), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Page page = pageService.findById(id);
        Chapitre chapitre = page.getChapitre();
        chapitre.setTotalPages(chapitre.getTotalPages()-1);
        chapitreService.Save(chapitre);
        chapitre.getPages().remove(page);

        pageService.deleteByid(id);

        return new ResponseEntity<>(new MessageResponse("Page Deleted"),HttpStatus.OK);
    }

}
