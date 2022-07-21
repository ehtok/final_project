package by.roman.company.Service;

import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Vacancy;
import org.springframework.data.domain.Page;

public interface VacancyService {
    Vacancy saveVacancy(VacancyDTO vacancy);

    VacancyDTO findVacancyById(Integer id);

    void deleteVacancy(Integer id);

    Page<VacancyDTO> findAllVacancyWithSort(String field, String direction, int pageNumber, int pageSize);

    Page<VacancyDTO> findAllVacancyWithValue(String field, String direction, int pageNumber, int pageSize, String value);
}
