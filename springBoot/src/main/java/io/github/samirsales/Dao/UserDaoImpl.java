package io.github.samirsales.Dao;

import io.github.samirsales.Entity.User;
import io.github.samirsales.Repository.UserRepository;
import io.github.samirsales.Util.TextEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public User getUserByLogin(String login, boolean active) {
        User user = active ? userRepository.findByLoginAndActiveTrue(login) : userRepository.findByLogin(login);

        if(user != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email, boolean active) {
        User user = active ? userRepository.findByEmailAndActiveTrue(email) : userRepository.findByEmail(email);

        if(user != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return user;
    }

    @Override
    public User getUserByAuthentication(User user) {
        User authUser = userRepository.findByLoginAndActiveTrue(user.getLogin());

        if(authUser != null && authUser.getPassword().equals(user.getPassword())){

            // encoding password
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(authUser.getPassword());
            authUser.setPassword(hashedPassword);

            return authUser;
        }
        return null;
    }

    @Override
    public void removeUserById(long id) {
        User user = userRepository.findByIdAndActiveTrue(id);
        if(user != null) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void insertUser(User user) {
        TextEncryption textEncryption = new TextEncryption();
        user.setPassword(textEncryption.getMD5(user.getPassword()));

        userRepository.save(user);
    }
}
