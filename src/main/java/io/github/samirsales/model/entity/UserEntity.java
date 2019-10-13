package io.github.samirsales.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.github.samirsales.model.enums.Gender;
import io.github.samirsales.model.enums.Role;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
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
    private String username;

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
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(id, userEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
