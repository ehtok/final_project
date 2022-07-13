package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ACTIVE("Открыта"),
    OFF("Закрыта");

    private final String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
