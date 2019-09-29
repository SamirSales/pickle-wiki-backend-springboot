package io.github.samirsales.model.enums;

public enum ImageType {
    ARTICLE("ARTICLE"),
    PROFILE("PROFILE");

    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
