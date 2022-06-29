package com.example.Elearning.Repositorys.FilesRepo;

import com.example.Elearning.Models.FilesModules.File;
import org.springframework.content.commons.repository.ContentStore;

public interface FileContent extends ContentStore<File, String> {
    File SaveFile(File file);
}
