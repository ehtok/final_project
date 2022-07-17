package by.roman.company.Service;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Entity.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    Company saveCompany(CompanyDTO company);

    void deleteCompany(Integer id);

    CompanyDTO findCompanyDTOById(Integer id);

    Page<CompanyDTO> findAllCompanyWithSort(String field, String direction, int pageNumber, int pageSize);
    Page<CompanyDTO> findByNameContaining(String name, String field, String direction, int pageNumber, int pageSize);

    List<Vacancy> findVaca(Integer id);
}
