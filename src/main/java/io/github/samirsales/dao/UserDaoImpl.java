package io.github.samirsales.dao;

import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@SuppressWarnings("unused")
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
    public UserEntity getActiveByLogin(String login) {
        return userRepository.findByLoginAndActiveTrue(login);
    }

    @Override
    public UserEntity getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserEntity getActiveByEmail(String email) {
        return userRepository.findByEmailAndActiveTrue(email) ;
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity getByAuthentication(UserEntity userEntity) {
        return userRepository.findByLoginAndActiveTrue(userEntity.getLogin());
    }

    @Override
    public void deleteById(long id) {
        UserEntity userEntity = getInactivatedEntityById(id);
        if(userEntity != null) {
            userRepository.save(userEntity);
        }
    }

    private UserEntity getInactivatedEntityById(long id){
        UserEntity userEntity = getById(id);

        if(userEntity != null){
            final boolean activeStatus = false;

            return new UserEntity(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getLogin(),
                    userEntity.getEmail(),
                    activeStatus,
                    userEntity.getPassword(),
                    userEntity.getGender(),
                    userEntity.getRoleEntities(),
                    userEntity.getImageEntity()
            );
        }
        return null;
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void create(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
