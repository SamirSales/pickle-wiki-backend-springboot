package io.github.samirsales.Dao;

import io.github.samirsales.Entity.User;
import io.github.samirsales.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("postgres")
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findByActiveTrue();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findByIdAndActiveTrue(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLoginAndActiveTrue(login);
    }

    @Override
    public User getUserByAuthentication(User user) {
        User authUser = userRepository.findByLoginAndActiveTrue(user.getLogin());

        if(authUser != null && authUser.getPassword().equals(user.getPassword())){
            return authUser;
        }

        return null;
    }

    @Override
    public void removeUserById(long id) {
        User user = userRepository.findByIdAndActiveTrue(id);
        if(user != null) {
            user.setActive(false);
            // todo: reset update time
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        // todo: reset update time
        userRepository.save(user);
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }
}
