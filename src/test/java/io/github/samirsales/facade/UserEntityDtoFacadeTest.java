package io.github.samirsales.facade;

import io.github.samirsales.UserEntityGenerator;
import io.github.samirsales.model.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserEntityDtoFacadeTest {

    private UserEntityDtoFacade userEntityDtoFacade = null;

    @Before
    public void setUp(){
        userEntityDtoFacade = new UserEntityDtoFacade();
    }

    @Test
    public void getEntityWithUpdatedPasswordTest(){
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        String newPasswordNotEncrypted = userEntity.getPassword();
        String encryptedPasswordFor123456 = "e10adc3949ba59abbe56e057f20f883e";
        UserEntity userEntityNewPassword = userEntityDtoFacade
                .getEntityWithUpdatedPassword(userEntity, newPasswordNotEncrypted);

        assertEquals(userEntity.getId(), userEntityNewPassword.getId());
        assertEquals(userEntity.getName(), userEntityNewPassword.getName());
        assertEquals(userEntity.getUsername(), userEntityNewPassword.getUsername());
        assertEquals(userEntity.getEmail(), userEntityNewPassword.getEmail());
        assertEquals(userEntity.isActive(), userEntityNewPassword.isActive());
        assertEquals(encryptedPasswordFor123456, userEntityNewPassword.getPassword());
        assertEquals(userEntity.getGender(), userEntityNewPassword.getGender());
        assertEquals(userEntity.getRoleEntities(), userEntityNewPassword.getRoleEntities());
        assertEquals(userEntity.getImageProfile(), userEntityNewPassword.getImageProfile());
    }
}
