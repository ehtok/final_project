package by.roman.company.DTO;

import by.roman.company.Entity.Company;
import by.roman.company.Entity.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;

    private String name;

    private String dateStart;

    private String dateFinish;

    private String description;

    private String location;

    private String companyName;

    private Company company;



}
