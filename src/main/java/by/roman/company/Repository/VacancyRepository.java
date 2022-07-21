package by.roman.company.Repository;

import by.roman.company.Entity.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    @Query("select vacancy from Vacancy vacancy where " +
            "concat(vacancy.name,vacancy.professionLevel,vacancy.location) like %?1%")
    Page<Vacancy> findVacanciesByNameAndProfessionLevel(String value, Pageable pageable);


}
