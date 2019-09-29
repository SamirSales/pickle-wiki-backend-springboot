package io.github.samirsales.dao;

import io.github.samirsales.model.entity.UserEntity;

import java.util.Collection;

public interface UserDao {
    Collection<UserEntity> getAll();

    UserEntity getById(long id);

    UserEntity getActiveByLogin(String login);

    UserEntity getByLogin(String login);

    UserEntity getActiveByEmail(String email);

    UserEntity getByEmail(String email);

    UserEntity getByAuthentication(UserEntity userEntity);

    void deleteById(long id);

    void update(UserEntity userEntity);

    void create(UserEntity userEntity);
}
