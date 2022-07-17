package by.roman.company.Repository;

import by.roman.company.Entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    @Query("from Vacancy where name like lower(concat('%', :name, '%')) " +
            "or professionLevel like lower(concat('%', :name, '%'))")
    List<Vacancy> findVacanciesBy(@Param("name") String search);

    @Query("from Vacancy where company.id like :id")
    List<Vacancy> findVacancyWithCompany(@Param("id") Integer id);
}
