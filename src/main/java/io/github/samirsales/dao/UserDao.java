package io.github.samirsales.dao;

import io.github.samirsales.model.entity.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAll();

    User getById(long id);

    User getByLogin(String login, boolean active);

    User getByEmail(String login, boolean active);

    User getByAuthentication(User user);

    void deleteById(long id);

    void update(User user);

    void insert(User user);
}
