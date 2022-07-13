package by.roman.company.Service;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.Entity.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company saveCompany(CompanyDTO company);

    void deleteCompany(Integer id);

    CompanyDTO findCompanyDTOById(Integer id);

    CompanyDTO findCompanyByName(String name);

    Page<CompanyDTO> findAllCompanyWithSort(String field, String direction, int pageNumber, int pageSize);

    Company findCompanyById(Integer id);

    List<Company> findAll();
}
