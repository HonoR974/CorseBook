package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.dto.CompanyDTO;
import com.back.springboot.exception.ResourceNotFoundException;
import com.back.springboot.models.Adress;
import com.back.springboot.models.Company;
import com.back.springboot.repository.AdressRepository;
import com.back.springboot.repository.CompanyRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService  {


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ModelMapper modelMapper;


    //---------------- CRUD 
    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
      
        return companyRepository.save(company);
    }
    

    
    @Override
    public Company getCompanyById(long id) {


        Company company = companyRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Company not extist with id : "+ id) ) ;
 
        return company ;
    }



    @Override
    public Company updateCompany(long id, Company companyRequest)
    {

        Company company = companyRepository.findById(id)
                                            .orElseThrow( () ->  new ResourceNotFoundException("Company not extist with id : "+ id));


        company.setAdress(companyRequest.getAdress());
        company.setName(companyRequest.getName());


        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(long id)
    {
        Company company = companyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Company not extist with id : "+ id) ) ;

        companyRepository.delete(company);
    }

    //----------------- Covnert DTO ------------------//


    //from company to companyDTO 
    @Override
    public CompanyDTO convertTodDto(Company company)
    {
        CompanyDTO companyDTO =  modelMapper.map(company, CompanyDTO.class);
        
        companyDTO.setCodePostal(company.getAdress().getCodePostal());
        companyDTO.setVille(company.getAdress().getVille());
        companyDTO.setInfoAdress(company.getAdress().getInfoAdress());



        return companyDTO;
    }

    //from list company to list dto 
    @Override
    public List<CompanyDTO> convertLstCompToListCompDto( List<Company> list )
    {
        List<CompanyDTO> listDTO = new ArrayList<>();

        for (Company company : list)
        {
         listDTO.add(convertTodDto(company));

        }
        return listDTO;
    }


    //from companyDTO to company 
    public Company convertToEntity(CompanyDTO companyDTO) 
    {

        Company company = modelMapper.map(companyDTO, Company.class);


        Adress adress = new Adress();
        adress.setCodePostal(companyDTO.getCodePostal());
        adress.setInfoAdress(companyDTO.getInfoAdress());
        adress.setVille(companyDTO.getVille());

        company.setAdress(adress);
        

        return company;
    }



  
    
}
