package io.github.samirsales.Dao;

import io.github.samirsales.Entity.Enum.Gender;
import io.github.samirsales.Entity.Enum.UserType;
import io.github.samirsales.Entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeUserDaoImpl implements UserDao {

    private static Map<Long, User> users;

    static {
        users = new HashMap<Long, User>(){
            {
                put((long) 1, new User(1L, "Samir Sales", "samir",
                        "samir@email.com","123456", Gender.MALE, UserType.ADMIN));
                put((long) 2, new User(2L, "Andrio Antônio", "andrio",
                        "andrio@email.com","111111", Gender.MALE, UserType.EDITOR));
                put((long) 3, new User(3L, "Diego Maia", "diego",
                        "diego@email.com","222222", Gender.MALE, UserType.EDITOR));
                put((long) 4, new User(4L, "John Alisson", "john",
                        "john@email.com","333333", Gender.MALE, UserType.EDITOR));
                put((long) 5, new User(5L, "Fabiana Ângelo", "fabi",
                        "fabi@email.com","333333", Gender.FEMALE, UserType.EDITOR));
            }
        };
    }

    @Override
    public Collection<User> getAllUsers(){
        return this.users.values();
    }

    @Override
    public User getUserById(long id){
        return this.users.get(id);
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
