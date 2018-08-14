package io.github.samirsales.Entity.Dto;

import io.github.samirsales.Entity.Permission;
import io.github.samirsales.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private String email;
    private boolean active;
    private String gender;
    private List<Permission> permissions;

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        login = user.getLogin();
        email = user.getEmail();
        active = user.isActive();
        gender = user.getGender().getValue();

        permissions = new ArrayList<>();
        for(Permission permission : user.getPermissions()){
            permissions.add(new Permission(permission.getId(), permission.getPermissionType(), null));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
