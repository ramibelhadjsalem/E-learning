package com.example.Elearning.Services.VideoServices;

import com.example.Elearning.Storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class StreamingVideos {
    @Autowired private StorageService storageService;

    @Autowired private ResourceLoader resourceLoader;
    public StreamingVideos() {
    }

    public Mono<Resource> getVideo(String title) throws IOException {
                return Mono.fromSupplier(()->{
                    Resource file = storageService.loadAsResource("video/"+title);
                    return file;
                }).log("downloding video")
                        .delaySubscription(Duration.ofMillis(500));


    }
    public Mono<byte[]> getPartOfvideo(String title) throws IOException{

        return Mono.fromSupplier(()->{
            Resource file = storageService.loadAsResource("video/"+title);
            try {
                InputStream inputStream = file.getInputStream();
                byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
                byte[] slicedArray = Arrays.copyOfRange(bdata, 0,bdata.length/2);
                return slicedArray;
            }catch (IOException ex){

            }
            return null;
        }) .delaySubscription(Duration.ofMillis(500));
    }
}
