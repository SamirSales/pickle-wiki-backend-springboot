package io.github.samirsales.model.enums;

public enum PictureType {
    DEFAULT("DEFAULT"), PROFILE("PROFILE");

    private String value;

    PictureType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
