package com.example.Elearning.Storage;

import com.example.Elearning.Storage.exception.StorageException;
import com.example.Elearning.Storage.exception.StorageFileNotFoundException;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService{

    private  Path rootLocation;
    public FileSystemStorageService() {
        this.rootLocation=Paths.get("./upload-dir");
    }

    @Override
    public void init() {
        try {
            if(Files.notExists(Paths.get(rootLocation.toString()))){
                Files.createDirectory(Paths.get(String.valueOf(rootLocation)));
                }

        }catch (Exception ex){
            throw new SecurityException("could not init diractory",ex);
        }
    }

    @Override
    public String store(MultipartFile file,String fileType) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        filename = filename.toLowerCase().replaceAll(" ", "-");
        filename = String.valueOf(fileType+"/"+Calendar.getInstance().getTimeInMillis())+filename;

        try {
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file ");
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/upload-dir/")
                    .path(filename)
                    .toUriString();

            return url ;
        }catch (IOException e) {
            throw new StorageException("Failed to store file: " + file.getOriginalFilename(), e);
        }

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename){return rootLocation.resolve(filename);
    }

    @Override
    public UrlResource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            UrlResource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
    }

    @Override
    public void deleteByPath(String path) {

        String fileName = path.substring(path.lastIndexOf("/")+1, path.length());
        Path file = load(fileName);

        try {
            UrlResource resource = new UrlResource(file.toUri());
            FileSystemUtils.deleteRecursively(resource.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
