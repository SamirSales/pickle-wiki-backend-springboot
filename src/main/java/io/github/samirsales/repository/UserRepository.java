package io.github.samirsales.repository;

import io.github.samirsales.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Collection<UserEntity> findByActiveTrueOrderByName();

    Optional<UserEntity> findByIdAndActiveTrue(Long id);

    Optional<UserEntity>  findByLoginAndActiveTrue(String login);

    Optional<UserEntity>  findByEmailAndActiveTrue(String login);

    Optional<UserEntity>  findByLogin(String login);

    Optional<UserEntity>  findByEmail(String login);
}
