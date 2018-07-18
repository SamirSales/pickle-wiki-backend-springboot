package io.github.samirsales.Service;

import io.github.samirsales.Dao.UserDao;
import io.github.samirsales.Entity.Enum.UserPermission;
import io.github.samirsales.Entity.User;
import io.github.samirsales.Exception.AuthorizationException;
import io.github.samirsales.Security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    @Qualifier("fakeData")
    private UserDao userDao;

    public static UserSS authenticated(){
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public Collection<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User getUserById(Long id){

        UserSS userSS = UserService.authenticated();

        if(userSS == null || (!userSS.hashRole(UserPermission.ADMIN) && !id.equals(userSS.getId()))){
            throw new AuthorizationException("Access Denied");
        }

        return this.userDao.getUserById(id);
    }

    public User getUserByAuthentication(User user) {
        return this.userDao.getUserByAuthentication(user);
    }

    public void removeUserById(long id) {
        this.userDao.removeUserById(id);
    }

    public void updateUser(User user){
        this.userDao.updateUser(user);
    }

    public void insertUser(User user) {
        this.userDao.insertUser(user);
    }
}
