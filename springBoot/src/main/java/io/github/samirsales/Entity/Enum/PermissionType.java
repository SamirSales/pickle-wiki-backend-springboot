package io.github.samirsales.Entity.Enum;

public enum PermissionType {
    EDITOR("ROLE_EDITOR"), ADMIN("ROLE_ADMIN");

    private String value;

    PermissionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
