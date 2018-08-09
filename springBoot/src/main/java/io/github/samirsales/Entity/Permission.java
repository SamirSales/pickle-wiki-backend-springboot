package io.github.samirsales.Entity;

import io.github.samirsales.Entity.Enum.PermissionType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PermissionType permissionType;

    @ManyToMany(mappedBy = "permissions")
    private List<User> users;

    public Permission(){

    }

    public Permission(Long id, PermissionType permissionType, List<User> users) {
        this.id = id;
        this.permissionType = permissionType;
        this.users = users;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) &&
                permissionType == that.permissionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, permissionType);
    }
}
