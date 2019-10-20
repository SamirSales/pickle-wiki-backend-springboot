package io.github.samirsales.dao;

import io.github.samirsales.model.entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
public interface UserDao {
    Collection<UserEntity> getAll();

    Optional<UserEntity> getById(long id);

    Optional<UserEntity> getActiveByLogin(String login);

    Optional<UserEntity> getByLogin(String login);

    Optional<UserEntity> getActiveByEmail(String email);

    Optional<UserEntity> getByEmail(String email);

    Optional<UserEntity> getByAuthentication(UserEntity userEntity);

    void deleteById(long id);

    void update(UserEntity userEntity);

    void create(UserEntity userEntity);
}
