package by.roman.company.Repository;

import by.roman.company.Entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("from Company where name like %:name%")
    List<Company> findCompaniesByName(@Param("name") String name);

    Page<Company> findByNameContaining(String name, Pageable pageable);


}
