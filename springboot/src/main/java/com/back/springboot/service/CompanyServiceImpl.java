package com.back.springboot.service;

import java.util.ArrayList;
import java.util.List;

import com.back.springboot.dto.CompanyDTO;
import com.back.springboot.models.Adress;
import com.back.springboot.models.Company;
import com.back.springboot.repository.CompanyRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService  {


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CompanyServiceImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }

    PropertyMap<CompanyDTO, Company> skipModifiedFieldsMap = new PropertyMap<CompanyDTO, Company>() {
        protected void configure() {
           skip().setAdress(null);
       }
     };

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    //from company to companyDTO 
    @Override
    public CompanyDTO convertTodDto(Company company)
    {
        CompanyDTO companyDTO =  modelMapper.map(company, CompanyDTO.class);
        
        //la classe adresse devient les attribut de companyDTO 
        companyDTO.setAdresse(company.getAdress().getCodePostal(),
                             company.getAdress().getVille(), 
                             company.getAdress().getAdresseComplet());


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
    public Company convertToEntity(CompanyDTO companyDTO) throws ParseException 
    {

        Company company = modelMapper.map(companyDTO, Company.class);
        Adress adress = new Adress(companyDTO.getCodePostal(), 
                                    companyDTO.getVille(),
                                    companyDTO.getAdresseComplete());

        company.setAdress(adress);
        return company;
    }


    @Override
    public Company createCompany(Company company) {
        // TODO Auto-generated method stub

        return companyRepository.save(company);
    }
    

    
}
