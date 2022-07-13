package by.roman.company.Service;

import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Vacancy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    Vacancy saveVacancy(VacancyDTO vacancy);

    VacancyDTO findVacancyById(Integer id);

    Vacancy updateVacancy(Integer id);

    void deleteVacancy(Integer id);

    List<VacancyDTO> findVacanciesByName(String name);

    Page<VacancyDTO> findAllVacancyWithSort(String field, String direction, int pageNumber, int pageSize);
}
