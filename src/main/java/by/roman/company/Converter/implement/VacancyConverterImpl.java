package by.roman.company.Converter.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Technology;
import by.roman.company.Entity.Vacancy;
import by.roman.company.Enum.EnglishLevelEnum;
import by.roman.company.Enum.ProfLevelEnum;
import by.roman.company.Enum.StatusEnum;
import by.roman.company.Enum.WorkingTimeEnum;

import java.util.List;
import java.util.stream.Collectors;

public class VacancyConverterImpl implements Converter<Vacancy, VacancyDTO> {

    @Override
    public VacancyDTO toDTO(Vacancy vacancy) {
        VacancyDTO vacancyDTO = VacancyDTO.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .workingTime(vacancy.getWorkingTime().getName())
                .experience(vacancy.getExperience())
                .englishLevel(vacancy.getEnglishLevel().getName())
                .professionLevel(vacancy.getProfessionLevel().getName())
                .salary(vacancy.getSalary())
                .status(vacancy.getStatus().getName())
                .companyName(vacancy.getCompany() == null ? null : vacancy.getCompany().getName())
                .technology(vacancy.getTechnologies().stream().map(Technology::getName).collect(Collectors.joining(",")))
                .location(vacancy.getLocation())
                .build();
        return vacancyDTO;
    }

    @Override
    public List<VacancyDTO> toListDTO(List<Vacancy> vacancies) {
        return vacancies.stream().map(
                        this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Vacancy toEntity(VacancyDTO vacancyDTO) {
        Vacancy vacancy = Vacancy.builder()
                .id(vacancyDTO.getId())
                .name(vacancyDTO.getName())
                .workingTime(WorkingTimeEnum.valueOf(vacancyDTO.getWorkingTime()))
                .experience(vacancyDTO.getExperience())
                .englishLevel(EnglishLevelEnum.valueOf(vacancyDTO.getEnglishLevel()))
                .professionLevel(ProfLevelEnum.valueOf(vacancyDTO.getProfessionLevel()))
                .salary(vacancyDTO.getSalary())
                .status(StatusEnum.valueOf(vacancyDTO.getStatus()))
                .location(vacancyDTO.getLocation())
                .company(vacancyDTO.getCompany())
                .build();
        return vacancy;
    }
}
