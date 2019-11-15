package io.github.samirsales.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.github.samirsales.model.enums.Gender;
import io.github.samirsales.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity(name = "users")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Getter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "The 'name' attribute must be filled.")
    @Column(nullable = false)
    private String name;

    @NaturalId(mutable = true)
    @NotEmpty(message = "The 'username' attribute must be filled.")
    @Column(nullable = false, unique=true)
    private String username;

    @NaturalId(mutable = true)
    @Email
    @Column(nullable = false, unique=true)
    private String email;

    private boolean active;

    @NotEmpty(message = "The 'password' attribute must be filled.")
    private String password;

    @Enumerated
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roleEntities;

    @OneToOne
    private ImageEntity imageProfile;

    public boolean hasRole(Role role){
        for(RoleEntity roleEntity : roleEntities){
            if(roleEntity.getRole().equals(role)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return active == that.active &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                gender == that.gender &&
                Objects.equals(roleEntities, that.roleEntities) &&
                Objects.equals(imageProfile, that.imageProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, active, password, gender, roleEntities, imageProfile);
    }
}
