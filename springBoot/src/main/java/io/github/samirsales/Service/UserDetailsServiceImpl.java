package io.github.samirsales.Service;

import io.github.samirsales.Dao.UserDao;
import io.github.samirsales.Entity.User;
import io.github.samirsales.Security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("fakeData")
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userDao.getUserByLogin(login);

        if(user == null){
            throw new UsernameNotFoundException(login);
        }

        return new UserSS(user.getId(), user.getLogin(), user.getPassword(), user.getUserPermissions());
    }
}
