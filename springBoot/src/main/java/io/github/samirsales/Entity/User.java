package io.github.samirsales.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.samirsales.Entity.Enum.Gender;
import io.github.samirsales.Entity.Enum.UserPermission;
import io.github.samirsales.Model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(nullable = false)
    private String login;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(nullable = false)
    private String email;

    @NotEmpty
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonManagedReference
    @OneToMany(targetEntity = PermissionEntity.class)
    private Set<PermissionEntity> userPermissions;

    public User(Long id, String name, String login, String email, String password, Gender gender) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.userPermissions = new HashSet<>();
    }

    public User() {
        this.userPermissions = new HashSet<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<PermissionEntity> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<PermissionEntity> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
