package io.github.samirsales.configuration;

import io.github.samirsales.dao.RoleDao;
import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;
import io.github.samirsales.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@SuppressWarnings("unused")
public class DatabaseInit {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @PostConstruct
    public void callInitializationMethods(){
        insertRolesOnDatabase();
        createUserAdminIfThereIsNoAdmin();
    }

    private void insertRolesOnDatabase() {
        roleDao.createIfNotExist(Role.ADMIN);
        roleDao.createIfNotExist(Role.EDITOR);
    }

    private void createUserAdminIfThereIsNoAdmin() {
        if(!isThereAnyAdminUserOnDatabase()){
            UserEntity userEntityAdmin = getDefaultAdminUserEntity();
            userDao.create(userEntityAdmin);
        }
    }
    
    private UserEntity getDefaultAdminUserEntity(){
        final Long id = null;
        String name = "Admin";
        String login = "admin";
        String email = "admin@email.com";
        String password = "admin";
        Gender gender = Gender.MALE;
        final boolean active = true;
        Set<RoleEntity> roleEntities = new HashSet<>();

        Optional<RoleEntity> adminRoleEntityOptional = roleDao.getEntityByRole(Role.ADMIN);
        adminRoleEntityOptional.ifPresent(roleEntities::add);

        Optional<RoleEntity> editorRoleEntityOptional = roleDao.getEntityByRole(Role.EDITOR);
        editorRoleEntityOptional.ifPresent(roleEntities::add);

        return new UserEntity(id, name, login, email, password, gender, active, roleEntities);
    }

    private boolean isThereAnyAdminUserOnDatabase(){
        List<UserEntity> userEntities = (List<UserEntity>) userDao.getAll();

        for(UserEntity userEntity : userEntities){
            if(userEntity.hasRole(Role.ADMIN)){
                return true;
            }
        }
        return  false;
    }
}
