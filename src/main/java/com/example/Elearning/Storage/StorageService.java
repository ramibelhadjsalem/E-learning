package com.example.Elearning.Storage;


import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    String store(MultipartFile file,String fileType);

    Stream<Path> loadAll();

    Path load(String filename);

    UrlResource loadAsResource(String filename);

    void deleteAll();
    void deleteByPath(String path);
}
