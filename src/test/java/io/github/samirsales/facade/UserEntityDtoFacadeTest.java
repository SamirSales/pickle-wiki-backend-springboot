package io.github.samirsales.facade;

import io.github.samirsales.dao.RoleDao;
import io.github.samirsales.dao.UserDao;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.runner.ParallelRunner;
import io.github.samirsales.utils.UserEntityGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(ParallelRunner.class)
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
