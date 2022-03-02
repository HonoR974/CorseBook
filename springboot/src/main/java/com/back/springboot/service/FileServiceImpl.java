package com.back.springboot.service;

import java.util.List;

import com.back.springboot.models.File;
import com.back.springboot.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileRepository fileRepository;

    public File createFile(File file)
    {

        File fileCreated = new File();
        fileCreated.setName(file.getName());
        fileCreated.setUrl(file.getUrl());
        System.out.println("\n file created " +  fileCreated.toString());
        return fileRepository.save(fileCreated);
    }

    public List<File> getAll()
    {
        return fileRepository.findAll();
    }

    //------------- Convert -------------//

    
    
}
