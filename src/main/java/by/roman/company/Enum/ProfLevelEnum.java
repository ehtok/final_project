package by.roman.company.Enum;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProfLevelEnum {
    INTERN("Intern"),
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    LEAD("Team Lead");

    private final String value;

    ProfLevelEnum(String name) {
        this.value = name;
    }

//    public static ProfLevelEnum fromString(String name) {
//        return Arrays.stream(ProfLevelEnum.values())
//                .filter(profLevelEnum -> profLevelEnum.name().equals(name))
//                .findFirst()
//                .orElse(null);
//    }
}
