package io.github.samirsales.repository;

import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findById(Long id);

    Optional<RoleEntity> findByRole(Role role);

    Collection<RoleEntity> findAll();
}
