package com.example.Elearning.Controllers;



import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.VideosModels.Video;
import com.example.Elearning.Services.PagesServices.PageService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Services.VideoServices.VideoService;
import com.example.Elearning.Storage.StorageService;
import com.example.Elearning.Utils.TokenUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/video")
public class VideoController {
    @Autowired private StorageService storageService;
    @Autowired private PageService pageService;
    @Autowired private TokenUtils tokenUtils;
    @Autowired private UserService userService;
    @Autowired private VideoService videoService;
    public VideoController() {
    }

    @PostMapping
    @JsonView(View.base.class)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file,
                                             @RequestParam("page_id") Long page_id,
                                             @RequestParam("description") String description){

        if(file.isEmpty() ) return ResponseEntity.badRequest().build();


        Video video=new Video(null,
                description,
                null,
                pageService.findById(page_id),
                userService.findById(tokenUtils.ExtractId())
        );
        video.setVideoUrl(storageService.store(file,"video" ));

        return  new ResponseEntity<>(videoService.Save(video),HttpStatus.OK);

    }

    @GetMapping("/ouwn")
    @JsonView(View.base.class)
    public ResponseEntity<?> findOuwnVideos(){
        return new ResponseEntity<>(videoService.findByUserid(tokenUtils.ExtractId()),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @JsonView(View.base.class)
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(videoService.findById(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id){
        Video video = videoService.findById(id);
        if(video.getUser().getId()!=tokenUtils.ExtractId()){
            return new ResponseEntity(new MessageResponse("Not allawed to delete this video"),HttpStatus.FORBIDDEN);
        }
        storageService.deleteByPath(video.getVideoUrl());
        videoService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("video deleted"));
    }
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') ")
    public ResponseEntity<?> deleteVideoOfAdmin(@PathVariable Long id){
        videoService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("video deleted"));
    }






























  /*  @GetMapping("/")
    public Map<String, String> listUploadedFiles(Model model) {
        List<String> files = storageService.loadAll().collect(Collectors.toList()).stream()
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());

     Map<String, String> fileLinks = files.stream()
                .collect(Collectors.toMap(
                        filename -> filename,
                        filename -> MvcUriComponentsBuilder.fromMethodName(VideoController.class, "serveFile", filename).build().toString()
                ));

      *//*     Map<String, String> fileJobLinks = files.stream()
                .collect(Collectors.toMap(
                        filename -> filename,
                        filename -> MvcUriComponentsBuilder.fromMethodName(JobLauncherController.class, "launchJob", storageService.load(filename).toAbsolutePath().toUri().toString()).build().toString()
                ));

        model.addAttribute("files", files);
        model.addAttribute("fileLinks", fileLinks);
        model.addAttribute("fileJobLinks", fileJobLinks);*//*

        return  fileLinks;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable String filename) {
   *//* Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);*//*
        UrlResource filePath = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION).body(filePath);

     *//*   InputStreamResource inputStreamResource = new InputStreamResource();
        HttpHeaders headers = new HttpHeaders();


        return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
*//*
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
*/


}
