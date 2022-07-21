package by.roman.company.Service.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.Converter.implement.CompanyConverterImpl;
import by.roman.company.DTO.CompanyDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Entity.Vacancy;
import by.roman.company.Repository.CompanyRepository;
import by.roman.company.Repository.VacancyRepository;
import by.roman.company.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.roman.company.Service.Constant.ONE;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private final Converter<Company, CompanyDTO> converter = new CompanyConverterImpl();

    @Override
    public Company saveCompany(CompanyDTO company) {
        return companyRepository.save(converter.toEntity(company));
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDTO findCompanyDTOById(Integer id) {
        return converter.toDTO(companyRepository.findById(id).orElse(null));
    }

    @Override
    public Page<CompanyDTO> findCompanyByNamePaginationAndSort
            (String name, String field, String direction, int pageNumber, int pageSize) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Company> companies = companyRepository.findByNameContaining
                (name, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return companies.map(converter::toDTO);
    }


    @Override
    public List<Vacancy> findVaca(Integer id) {
        return companyRepository.findVacancyWithCompany(id);
    }


}
