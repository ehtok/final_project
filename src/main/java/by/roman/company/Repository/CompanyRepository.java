package by.roman.company.Repository;

import by.roman.company.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("from Company where name like %:name%")
    Company findCompanyByName(@Param("name") String name);

}
