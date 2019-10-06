package io.github.samirsales.dao;

import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

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
    public Optional<UserEntity> getById(long id) {
        return userRepository.findByIdAndActiveTrue(id);
    }

    @Override
    public Optional<UserEntity> getActiveByLogin(String login) {
        return userRepository.findByUsernameAndActiveTrue(login);
    }

    @Override
    public Optional<UserEntity> getByLogin(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public Optional<UserEntity> getActiveByEmail(String email) {
        return userRepository.findByEmailAndActiveTrue(email) ;
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> getByAuthentication(UserEntity userEntity) {
        return userRepository.findByUsernameAndActiveTrue(userEntity.getUsername());
    }

    @Override
    public void deleteById(long id) {
        Optional<UserEntity> userEntityOptional = getInactivatedEntityById(id);
        userEntityOptional.ifPresent(userEntity -> userRepository.save(userEntity));
    }

    private Optional<UserEntity> getInactivatedEntityById(long id){
        Optional<UserEntity> userEntityOptional = getById(id);

        if(userEntityOptional.isPresent()){
            final UserEntity userEntity = userEntityOptional.get();
            final boolean activeStatus = false;

            UserEntity inactivatedUserEntity =  new UserEntity(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getUsername(),
                    userEntity.getEmail(),
                    activeStatus,
                    userEntity.getPassword(),
                    userEntity.getGender(),
                    userEntity.getRoleEntities(),
                    userEntity.getImageProfile()
            );
            return Optional.of(inactivatedUserEntity);
        }
        return Optional.empty();
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
