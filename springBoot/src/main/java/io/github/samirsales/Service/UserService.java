package io.github.samirsales.Service;

import io.github.samirsales.Dao.UserDao;
import io.github.samirsales.Entity.Dto.UserDTO;
import io.github.samirsales.Entity.Enum.PermissionType;
import io.github.samirsales.Entity.User;
import io.github.samirsales.Exception.AuthorizationException;
import io.github.samirsales.Security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    @Qualifier("postgres")
    private UserDao userDao;

    public static UserSS authenticated(){
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public List<UserDTO> getAllUsers(){
        Collection<User> users = userDao.getAllUsers();
        return users.parallelStream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){

        UserSS userSS = UserService.authenticated();

        if(userSS == null || (!userSS.hashRole(PermissionType.ADMIN) && !id.equals(userSS.getId()))){
            throw new AuthorizationException("Access Denied");
        }

        return new UserDTO(this.userDao.getUserById(id));
    }

    public UserDTO getUserByAuthentication(User user) {
        return new UserDTO(this.userDao.getUserByAuthentication(user));
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

    public UserDTO getUserByToken() {
        UserSS userSS = UserService.authenticated();

        if(userSS == null ){
            throw new AuthorizationException("Access Denied");
        }

        return getUserById(userSS.getId());
    }
}
