package io.github.samirsales.dao;

import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.repository.UserRepository;
import io.github.samirsales.util.TextEncryption;
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
    public Collection<UserEntity> getAll() {
        return userRepository.findByActiveTrueOrderByName();
    }

    @Override
    public UserEntity getById(long id) {
        return userRepository.findByIdAndActiveTrue(id);
    }

    @Override
    public UserEntity getByLogin(String login, boolean active) {
        UserEntity userEntity = active ? userRepository.findByLoginAndActiveTrue(login) : userRepository.findByLogin(login);

        if(userEntity != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(hashedPassword);
        }
        return userEntity;
    }

    @Override
    public UserEntity getByEmail(String email, boolean active) {
        UserEntity userEntity = active ? userRepository.findByEmailAndActiveTrue(email) : userRepository.findByEmail(email);

        if(userEntity != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(hashedPassword);
        }
        return userEntity;
    }

    @Override
    public UserEntity getByAuthentication(UserEntity userEntity) {
        UserEntity authUserEntity = userRepository.findByLoginAndActiveTrue(userEntity.getLogin());

        if(authUserEntity != null && authUserEntity.getPassword().equals(userEntity.getPassword())){

            // encoding password
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(authUserEntity.getPassword());
            authUserEntity.setPassword(hashedPassword);

            return authUserEntity;
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        UserEntity userEntity = userRepository.findByIdAndActiveTrue(id);
        if(userEntity != null) {
            userEntity.setActive(false);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void create(UserEntity userEntity) {
        TextEncryption textEncryption = new TextEncryption();
        userEntity.setPassword(textEncryption.getMD5(userEntity.getPassword()));

        userRepository.save(userEntity);
    }
}
