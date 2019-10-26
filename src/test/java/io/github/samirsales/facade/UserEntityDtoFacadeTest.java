package io.github.samirsales.facade;

import io.github.samirsales.UserEntityGenerator;
import io.github.samirsales.dao.RoleDao;
import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class UserEntityDtoFacadeTest {

    @InjectMocks
    private UserEntityDtoFacade userEntityDtoFacade;

    @Mock
    @SuppressWarnings("unused")
    private UserDao userDao;

    @Mock
    @SuppressWarnings("unused")
    private RoleDao roleDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
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

    @Test
    public void getActiveEntityByDTOTest(){
        Long userIdExample = 1L;
        UserEntity generatedUserEntity = UserEntityGenerator.getUserEntityGeneratedById(userIdExample);
        UserDTO userDTO = new UserDTO(generatedUserEntity);

        Mockito.when(userDao.getById(userIdExample)).thenReturn(Optional.of(generatedUserEntity));
        UserEntity userEntityByDTO = userEntityDtoFacade.getActiveEntitySetByDTO(userDTO);
        assertEquals(generatedUserEntity, userEntityByDTO);
    }

    @Test
    public  void getActiveEntitySetByIdAndDtoTest(){
        Long userIdExample = 1L;
        UserEntity generatedUserEntity = UserEntityGenerator.getUserEntityGeneratedById(userIdExample);
        UserDTO userDTO = new UserDTO(generatedUserEntity);

        Mockito.when(userDao.getById(userIdExample)).thenReturn(Optional.of(generatedUserEntity));
        UserEntity userEntityByDTO = userEntityDtoFacade.getActiveEntitySetByIdAndDTO(userIdExample, userDTO);
        assertEquals(generatedUserEntity, userEntityByDTO);
    }
}
