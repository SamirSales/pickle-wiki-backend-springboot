package io.github.samirsales.dao;

import io.github.samirsales.model.dto.RoleDTO;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {

    void createIfNotExist(Role role);

    Optional<RoleEntity> getEntityByRole(Role role);

    List<RoleDTO> getAll();
}
