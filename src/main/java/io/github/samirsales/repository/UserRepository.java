package io.github.samirsales.repository;

import io.github.samirsales.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {

    Collection<User> findByActiveTrueOrderByName();

    User findByIdAndActiveTrue(Long id);

    User findByLoginAndActiveTrue(String login);

    User findByEmailAndActiveTrue(String login);

    User findByLogin(String login);

    User findByEmail(String login);
}
