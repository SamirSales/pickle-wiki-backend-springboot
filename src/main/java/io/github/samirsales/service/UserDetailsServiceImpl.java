package io.github.samirsales.service;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserEntity userEntity = userDao.getActiveByLogin(login);

        if(userEntity == null){
            throw new UsernameNotFoundException(login);
        }

        return new UserSecurity(userEntity.getId(), userEntity.getLogin(), userEntity.getPassword(), userEntity.getRoleEntities());
    }
}
