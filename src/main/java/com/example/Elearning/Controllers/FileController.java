package com.example.Elearning.Controllers;

import com.example.Elearning.Services.VideoServices.StreamingVideos;
import com.example.Elearning.Storage.StorageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("upload-dir")
@Slf4j
public class FileController {
    @Autowired private StorageService storageService;
    @Autowired private StreamingVideos streamingVideos;
    @Autowired private ResourceLoader resourceLoader;
    public FileController() {
    }
    @SneakyThrows
    @GetMapping(value = "/video/{title}",produces = "video/mp4")
/*    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF') or hasRole('ROLE_USER') ")*/
    public Mono<?> getvideo(@PathVariable String title, @RequestHeader("Range") String range) throws IOException{
        /*return streamingVideos.getVideo(title);*/
        return streamingVideos.getPartOfvideo(title);

    }

    @GetMapping("note/{noteName}")
    public ResponseEntity<?> getNote(@PathVariable String noteName){
        byte[] bdata;
        Resource file = storageService.loadAsResource("note/"+noteName);
        try {

            InputStream inputStream = file.getInputStream();
            bdata = FileCopyUtils.copyToByteArray(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + noteName + "\"")
                .body(bdata);
    }
    @GetMapping("img/{imgname}")
    public  ResponseEntity<?> getImg(@PathVariable String imgname){
        byte[] bdata;
        Resource file = storageService.loadAsResource("img/"+imgname);
        try {

            InputStream inputStream = file.getInputStream();
            bdata = FileCopyUtils.copyToByteArray(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + imgname + "\"")
                .body(bdata);
    }


}
