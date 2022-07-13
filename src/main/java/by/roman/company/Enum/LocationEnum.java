package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum LocationEnum {
    MINSK("Minsk"),
    GRODNO("Grodno"),
    VITEBSK("Vitebsk"),
    MOGILEV("Mogilev"),
    BREST("Brest"),
    GOMEL("Gomel");

    private final String name;

    LocationEnum(String name) {
        this.name = name;
    }
}
