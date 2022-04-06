package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.FileDTO;
import com.back.springboot.models.File;

public interface FileService {
    File createFile(File file);

    List<File> getAll();

    File getFileById(long id );
    //convert 
    FileDTO convertToDTO(File file);
    
    List<FileDTO> convertToListeDTO(List<File> lFiles);
}
