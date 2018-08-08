package io.github.samirsales.Entity.Enum;

public enum Permission {
    EDITOR("ROLE_EDITOR"), ADMIN("ROLE_ADMIN");

    private String value;

    Permission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
