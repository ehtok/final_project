package by.roman.company.Converter.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.DTO.CompanyDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Entity.Course;
import by.roman.company.Entity.Vacancy;
import by.roman.company.Enum.CompanyTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyConverterImpl implements Converter<Company, CompanyDTO> {

    @Override
    public CompanyDTO toDTO(Company entity) {
        CompanyDTO companyDTO = CompanyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .companySize(entity.getCompanySize())
                .companyType(entity.getCompanyType().getName())
                .description(entity.getDescription())
                .vacancy(entity.getVacancies().stream().map(Vacancy::getName).collect(Collectors.joining(" ,")))
                .course(entity.getCourses().stream().map(Course::getName).collect(Collectors.joining(" ,")))
                .build();
        return companyDTO;
    }


    @Override
    public List<CompanyDTO> toListDTO(List<Company> companies) {
        return companies.stream().map(
                        this::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        Company company = Company.builder()
                .id(companyDTO.getId())
                .name(companyDTO.getName())
                .companySize(companyDTO.getCompanySize())
                .companyType(CompanyTypeEnum.valueOf(companyDTO.getCompanyType()))
                .description(companyDTO.getDescription())
                .build();
        return company;
    }
}
