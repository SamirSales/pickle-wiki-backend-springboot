package io.github.samirsales.service;

import io.github.samirsales.dao.UserDao;
import io.github.samirsales.entity.User;
import io.github.samirsales.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("postgres")
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userDao.getUserByLogin(login, true);

        if(user == null){
            throw new UsernameNotFoundException(login);
        }

        return new UserSS(user.getId(), user.getLogin(), user.getPassword(), user.getPermissions());
    }
}
