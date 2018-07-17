package io.github.samirsales.Entity.Enum;

public enum UserPermission {
    EDITOR("ROLE_EDITOR"), ADMIN("ROLE_ADMIN");

    private String value;

    UserPermission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
