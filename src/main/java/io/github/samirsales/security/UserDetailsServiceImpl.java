package io.github.samirsales.security;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userDao.getActiveByLogin(login);

        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            return getUserSecurityByEntity(userEntity);
        }

        throw new UsernameNotFoundException(login);
    }

    private UserSecurity getUserSecurityByEntity(UserEntity userEntity){
        String hashedPassword = getHashedPassword(userEntity.getPassword());

        return new UserSecurity(
            userEntity.getId(),
            userEntity.getLogin(),
            hashedPassword,
            userEntity.getRoleEntities());
    }

    private String getHashedPassword(String unencryptedPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(unencryptedPassword);
    }
}
