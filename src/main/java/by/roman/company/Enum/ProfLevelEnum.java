package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum ProfLevelEnum {
    INTERN("Intern"),
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    LEAD("Team Lead");

    private final String name;

    ProfLevelEnum(String name) {
        this.name = name;
    }
}
