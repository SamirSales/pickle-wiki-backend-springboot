package io.github.samirsales.model.dto;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.enums.Role;

public class RoleDTO {

    private Role role;

    public RoleDTO(RoleEntity roleEntity){
        this.role = roleEntity.getRole();
    }

    public Role getRole() {
        return role;
    }
}
