package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class RoleDTO {

    private Role role;

    public RoleDTO(RoleEntity roleEntity){
        this.role = roleEntity.getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return role == roleDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
