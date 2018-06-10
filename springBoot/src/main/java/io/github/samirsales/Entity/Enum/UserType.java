package io.github.samirsales.Entity.Enum;

public enum UserType {
    EDITOR("EDITOR"), ADMIN("ADMIN");

    private String value;

    UserType(String value) {
        this.value = value;
    }
}
