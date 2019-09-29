package io.github.samirsales.model.enums;

public enum Role {
    EDITOR("ROLE_EDITOR"), ADMIN("ROLE_ADMIN");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
