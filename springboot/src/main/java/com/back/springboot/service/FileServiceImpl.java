package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.dto.FileDTO;
import com.back.springboot.models.File;
import com.back.springboot.repository.FileRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private ModelMapper modelMapper;

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

    
    @Override
	public FileDTO convertToDTO(File file) {
	
        FileDTO fileDTO=  modelMapper.map(file, FileDTO.class);

		return fileDTO;
	}

	@Override
	public List<FileDTO> convertToListeDTO(List<File> lFiles) {
		
        List<FileDTO> list = new ArrayList();
        
        for( File file : lFiles){
            list.add(convertToDTO(file));
        }

		return list;
	}

}
