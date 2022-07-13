package by.roman.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Integer id;

    private String name;

    private Integer companySize;

    private String companyType;

    private String description;

    private String course;

    private String vacancy;


}
