package io.github.samirsales.Entity;

import io.github.samirsales.Entity.Enum.UserPermission;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserPermission userPermission;

    @Override
    public boolean equals(Object obj) {
        return userPermission.equals(obj);
    }

    public PermissionEntity(UserPermission userPermission){
        this.userPermission = userPermission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPermission getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(UserPermission userPermission) {
        this.userPermission = userPermission;
    }
}
