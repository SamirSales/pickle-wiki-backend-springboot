package io.github.samirsales.service;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(ParallelRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest(){
        List<UserEntity> userEntityMockListFromDB = new ArrayList<>(2);
        userEntityMockListFromDB.add(UserEntityGenerator.getUserEntityGeneratedById(1L));
        userEntityMockListFromDB.add(UserEntityGenerator.getUserEntityGeneratedById(2L));
        Mockito.when(userDao.getAll()).thenReturn(userEntityMockListFromDB);

        List<UserDTO> userDtoList = userService.getAll();
        assertEquals(2, userDtoList.size());
        assertEquals(1L, (long) userDtoList.get(0).getId());
        assertEquals(2L, (long) userDtoList.get(1).getId());
    }

    @Test
    public void getByIdTest() {
        UserEntity userEntityExample = UserEntityGenerator.getUserEntityGeneratedById(1L);
        Mockito.when(userDao.getById(1L)).thenReturn(Optional.of(userEntityExample));

        UserDTO userDTO = userService.getById(1L);
        assertEquals(1L, (long) userDTO.getId());
    }

    @Test
    public void getByIdNotFoundTest() {
        Mockito.when(userDao.getById(1L)).thenReturn(Optional.empty());
        UserDTO userDTO = userService.getById(1L);
        assertNull(userDTO);
    }
}
