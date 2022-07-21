package by.roman.company.Service.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.Converter.implement.VacancyConverterImpl;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Vacancy;
import by.roman.company.Repository.VacancyRepository;
import by.roman.company.Service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static by.roman.company.Service.Constant.ONE;


@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {


    private final VacancyRepository vacancyRepository;
    private Converter<Vacancy, VacancyDTO> converter = new VacancyConverterImpl();

    @Override
    @Transactional
    public Vacancy saveVacancy(VacancyDTO vacancy) {
        Vacancy entity = converter.toEntity(vacancy);
//        if (entity.getId() != null) {
//            entity.setTechnologies(vacancy.getTechnologies());
////            vacancyRepository.getOne(vacancy.getId()).getTechnologies()
//        }
        return vacancyRepository.save(entity);
    }

    @Override
    public VacancyDTO findVacancyById(Integer id) {
        return converter.toDTO(vacancyRepository.findById(id).orElse(null));
    }


    @Override
    public void deleteVacancy(Integer id) {
        vacancyRepository.deleteById(id);
    }


    @Override
    public Page<VacancyDTO> findAllVacancyWithSort(String field, String direction, int pageNumber, int pageSize) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Vacancy> vacancies = vacancyRepository.findAll(PageRequest.of(pageNumber - ONE, pageSize, sort));
        return vacancies.map(vacancy -> converter.toDTO(vacancy));
    }

    @Override
    public Page<VacancyDTO> findAllVacancyWithValue
            (String field, String direction, int pageNumber, int pageSize, String value) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Vacancy> vacancies = vacancyRepository.findVacanciesByNameAndProfessionLevel
                (value, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return vacancies.map(vacancy -> converter.toDTO(vacancy));
    }
}
