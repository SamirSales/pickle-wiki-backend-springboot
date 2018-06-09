package io.github.samirsales.Entity.Enum;

public enum UserType {
    USER("USER"), ADMIN("ADMIN");

    private String value;

    UserType(String value) {
        this.value = value;
    }
}
