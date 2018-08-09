package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Enum.Gender;
import io.github.samirsales.Entity.Enum.PermissionType;
import io.github.samirsales.Entity.Permission;
import io.github.samirsales.Entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeUserDaoImpl implements UserDao {

    private static Map<Long, User> users;

    public FakeUserDaoImpl(){
        users = new HashMap<Long, User>();

        Permission adminPermission = new Permission(1L, PermissionType.ADMIN, null);
        Permission editorPermission = new Permission(2L, PermissionType.EDITOR, null);

        User user1 = new User(1L, "Samir Sales", "admin",
                "samir@email.com","admin", Gender.MALE);
        user1.getPermissions().add(adminPermission);
        user1.getPermissions().add(editorPermission);

        User user2 = new User(2L, "Andrio Antônio", "andrio",
                "andrio@email.com","111111", Gender.MALE);
        user2.getPermissions().add(editorPermission);

        User user3 = new User(3L, "Diego Maia", "diego",
                "diego@email.com","222222", Gender.MALE);
        user3.getPermissions().add(editorPermission);

        User user4 = new User(4L, "John Alisson", "john",
                "john@email.com","333333", Gender.MALE);
        user4.getPermissions().add(editorPermission);

        User user5 = new User(5L, "Fabiana Ângelo", "fabi",
                "fabi@email.com","333333", Gender.FEMALE);
        user5.getPermissions().add(editorPermission);

        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);
        users.put(user4.getId(), user4);
        users.put(user5.getId(), user5);
    }

    private User getSecureUserCopy(User user){
        User copied = new User(user.getId(),
                user.getName(),
                user.getLogin(),
                user.getEmail(), "",
                user.getGender());

        copied.setPermissions(user.getPermissions());

        return copied;
    }

    @Override
    public Collection<User> getAllUsers(){

        ArrayList<User> usersArrayList = new ArrayList<>();
        Collection<User> users = this.users.values();

        for(User user : users){
            usersArrayList.add(getSecureUserCopy(user));
        }

        return usersArrayList;
    }

    @Override
    public User getUserById(long id){
        return this.users.get(id);
    }

    @Override
    public User getUserByLogin(String login) {
        Collection<User> users = this.users.values();

        for(User user : users){
            if(user.getLogin().equals(login) || user.getEmail().equals(login)){

                User userToReturn = new User();
                userToReturn.setId(user.getId());
                userToReturn.setPassword(user.getPassword());
                userToReturn.setLogin(user.getLogin());
                userToReturn.setEmail(user.getEmail());
                userToReturn.setGender(user.getGender());

                if(user.getPermissions().contains(PermissionType.EDITOR)){
                    userToReturn.getPermissions().add(new Permission(2L, PermissionType.EDITOR, null));
                }

                if(user.getPermissions().contains(PermissionType.ADMIN)){
                    userToReturn.getPermissions().add(new Permission(1L, PermissionType.ADMIN, null));
                }


                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(userToReturn.getPassword());
                userToReturn.setPassword(hashedPassword);

                return userToReturn;
            }
        }

        return null;
    }

    @Override
    public User getUserByAuthentication(User inputUser) {

        Collection<User> users = this.users.values();

        for(User user : users){
            if((user.getLogin().equals(inputUser.getLogin()) || user.getEmail().equals(inputUser.getLogin()))
                    && user.getPassword().equals(inputUser.getPassword())){
//                return getSecureUserCopy(user);

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);

                return user;
            }
        }

        return null;
    }

    @Override
    public void removeUserById(long id) {
        this.users.remove(id);
    }

    @Override
    public void updateUser(User user){
        this.users.put(user.getId(), user);

        User s = this.users.get(user.getId());
        s.setName(user.getName());
        s.setLogin(user.getLogin());
        s.setEmail(user.getEmail());
        s.setPassword(user.getPassword());
    }

    @Override
    public void insertUser(User user) {
        user.setId((long) users.size());

        while(users.get(user.getId()) != null){
            user.setId(user.getId() + 1);
        }

        this.users.put(user.getId(), user);
    }
}
