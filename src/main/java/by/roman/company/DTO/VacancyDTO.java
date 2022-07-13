package by.roman.company.DTO;

import by.roman.company.Entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDTO {
    private Integer id;

    private String name;

    private String workingTime;

    private String experience;

    private String englishLevel;

    private String professionLevel;

    private String salary;

    private String status;

    private String location;

    private String technology;

    private String companyName;

    private Company company;

}
