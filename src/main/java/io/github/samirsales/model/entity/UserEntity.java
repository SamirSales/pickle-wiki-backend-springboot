package io.github.samirsales.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.github.samirsales.model.enums.Gender;
import io.github.samirsales.model.enums.Role;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity(name = "users")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UserEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NaturalId(mutable = true)
    @NotEmpty
    @Column(nullable = false)
    private String login;

    @NaturalId(mutable = true)
    @Email
    @Column(nullable = false)
    private String email;

    private boolean active;

    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    private Set<RoleEntity> roleEntities;

    @OneToOne
    private ImageEntity imageEntity;

    public UserEntity(Long id, @NotNull @NotEmpty String name, @NotEmpty String login,
                      @Email String email, boolean active, @NotEmpty String password,
                      Gender gender, Set<RoleEntity> roleEntities, ImageEntity imageEntity) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.active = active;
        this.password = password;
        this.gender = gender;
        this.roleEntities = roleEntities;
        this.imageEntity = imageEntity;
    }

    public UserEntity() { /* Necessary for JPA */ }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public boolean hasRole(Role role){
        for(RoleEntity roleEntity : roleEntities){
            if(roleEntity.getRole().equals(role)){
                return true;
            }
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(id, userEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
