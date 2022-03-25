package com.back.springboot.controller;


import java.util.List;

import com.back.springboot.dto.FileDTO;
import com.back.springboot.models.File;
import com.back.springboot.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/file/")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping()
    public ResponseEntity<?> createFile(@RequestBody File file)
    {
        return ResponseEntity.ok(fileService.createFile(file));
    }

    @GetMapping()
    public ResponseEntity<  List<FileDTO>> getAllFile()
    {
        List<FileDTO> list = fileService.convertToListeDTO(fileService.getAll());
        return ResponseEntity.ok(list);
    }

    
    
}