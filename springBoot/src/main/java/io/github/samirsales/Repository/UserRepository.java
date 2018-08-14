package io.github.samirsales.Repository;

import io.github.samirsales.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {

    Collection<User> findByActiveTrue();

    User findByIdAndActiveTrue(Long id);

    User findByLoginAndActiveTrue(String login);
}
