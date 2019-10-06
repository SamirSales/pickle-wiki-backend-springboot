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
    @Column(nullable = false, unique=true)
    private String login;

    @NaturalId(mutable = true)
    @Email
    @Column(nullable = false, unique=true)
    private String email;

    private boolean active;

    @NotEmpty
    private String password;

    @Enumerated
    private Gender gender;

    @ManyToMany
    private Set<RoleEntity> roleEntities;

    @OneToOne
    private ImageEntity imageProfile;

    public UserEntity(Long id, @NotNull @NotEmpty String name, @NotEmpty String login,
                      @Email String email, boolean active, @NotEmpty String password,
                      Gender gender, Set<RoleEntity> roleEntities, ImageEntity imageProfile) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.active = active;
        this.password = password;
        this.gender = gender;
        this.roleEntities = roleEntities;
        this.imageProfile = imageProfile;
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

    public ImageEntity getImageProfile() {
        return imageProfile;
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
