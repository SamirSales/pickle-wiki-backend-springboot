package io.github.samirsales.model.enums;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
