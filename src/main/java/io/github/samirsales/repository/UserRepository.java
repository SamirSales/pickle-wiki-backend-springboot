package io.github.samirsales.repository;

import io.github.samirsales.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Collection<UserEntity> findByActiveTrueOrderByName();

    UserEntity findByIdAndActiveTrue(Long id);

    UserEntity findByLoginAndActiveTrue(String login);

    UserEntity findByEmailAndActiveTrue(String login);

    UserEntity findByLogin(String login);

    UserEntity findByEmail(String login);
}
