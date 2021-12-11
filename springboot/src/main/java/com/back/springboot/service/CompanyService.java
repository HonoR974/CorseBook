package com.back.springboot.service;

import java.util.List;

import com.back.springboot.dto.CompanyDTO;
import com.back.springboot.models.Company;

import org.springframework.expression.ParseException;

public interface CompanyService {

    //crud 
    List<Company> getAll();

    Company createCompany(Company company);

    Company getCompanyById(long id);


    //convert

    CompanyDTO convertTodDto(Company company);
    
    List<CompanyDTO> convertLstCompToListCompDto( List<Company> list );
    
    Company convertToEntity(CompanyDTO companyDTO) throws ParseException;
}
