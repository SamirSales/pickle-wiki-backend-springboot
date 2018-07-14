package io.github.samirsales.Dao;

import io.github.samirsales.Entity.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAllUsers();

    User getUserById(long id);

    User getUserByAuthentication(User user);

    void removeUserById(long id);

    void updateUser(User user);

    void insertUser(User user);
}
