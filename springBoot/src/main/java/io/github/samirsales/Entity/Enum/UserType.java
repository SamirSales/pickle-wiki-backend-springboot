package io.github.samirsales.Entity.Enum;

public enum UserType {
    EDITOR("ROLE_EDITOR"), ADMIN("ROLE_ADMIN");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
