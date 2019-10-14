package io.github.samirsales.facade;

import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SuppressWarnings("WeakerAccess")
public class UserEntityDtoFacadeTest {

    private static UserEntityDtoFacade userEntityDtoFacade = null;

    @BeforeAll
    public static void setUp(){
        userEntityDtoFacade = new UserEntityDtoFacade();
    }

    @Test
    public void getEntityWithUpdatedPasswordTest(){
        UserEntity userEntity = getUserEntity();
        String newPasswordNotEncrypted = "123456";
        String encryptedPassword = "e10adc3949ba59abbe56e057f20f883e";
        UserEntity userEntityNewPassword = userEntityDtoFacade
                .getEntityWithUpdatedPassword(userEntity, newPasswordNotEncrypted);

        assertEquals(userEntity.getId(), userEntityNewPassword.getId());
        assertEquals(userEntity.getName(), userEntityNewPassword.getName());
        assertEquals(userEntity.getUsername(), userEntityNewPassword.getUsername());
        assertEquals(userEntity.getEmail(), userEntityNewPassword.getEmail());
        assertEquals(userEntity.isActive(), userEntityNewPassword.isActive());
        assertEquals(encryptedPassword, userEntityNewPassword.getPassword());
        assertEquals(userEntity.getGender(), userEntityNewPassword.getGender());
        assertEquals(userEntity.getRoleEntities(), userEntityNewPassword.getRoleEntities());
        assertEquals(userEntity.getImageProfile(), userEntityNewPassword.getImageProfile());
    }

    private UserEntity getUserEntity(){
        Long id = 1L;
        String name = "Name";
        String username = "username";
        String email = "user@email.com";
        final boolean isActive = true;
        String password = "123456789";
        Gender gender = Gender.MALE;
        Set<RoleEntity> roleEntities = new HashSet<>();
        final ImageEntity imageProfile = null;
        return new UserEntity(id, name, username, email,isActive, password, gender, roleEntities, imageProfile);
    }
}
