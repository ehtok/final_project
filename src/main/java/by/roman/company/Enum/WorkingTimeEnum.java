package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum WorkingTimeEnum {
    FULL("Полный день"),
    PART("Частичная занятость"),
    REMOTE("Удаленная работа");

    private final String name;

    WorkingTimeEnum(String name) {
        this.name = name;
    }
}
