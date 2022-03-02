package com.back.springboot.service;

import java.util.List;

import com.back.springboot.models.File;

public interface FileService {
    File createFile(File file);

    List<File> getAll();

    
    
}
