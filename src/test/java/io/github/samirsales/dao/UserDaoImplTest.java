package io.github.samirsales.dao;

import io.github.samirsales.UserEntityGenerator;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl userDaoImpl;

    @Mock
    @SuppressWarnings("unused")
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll(){
        Mockito.when(userDaoImpl.getAll()).thenReturn(getUserEntityCollection());
        Collection<UserEntity> collection = userDaoImpl.getAll();
        assertEquals(2, collection.size());
    }

    @Test
    public void testGetAllWithoutEntities(){
        Mockito.when(userDaoImpl.getAll()).thenReturn(new ArrayList<>());
        Collection<UserEntity> collection = userDaoImpl.getAll();
        assertEquals(0, collection.size());
    }

    @Test
    public void testGetById(){
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        Mockito.when(userDaoImpl.getById(1L)).thenReturn(Optional.of(userEntity));
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getById(1L);
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetByIdNotFound(){
        Mockito.when(userDaoImpl.getById(1L)).thenReturn(Optional.empty());
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getById(1L);
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByLogin(){
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        Mockito.when(userDaoImpl.getByLogin(userEntity.getUsername())).thenReturn(Optional.of(userEntity));
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getByLogin(userEntity.getUsername());
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByLoginNotFound(){
        Mockito.when(userDaoImpl.getByLogin("username1")).thenReturn(Optional.empty());
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getByLogin("username1");
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByEmail(){
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        Mockito.when(userDaoImpl.getByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getByEmail(userEntity.getEmail());
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByEmailNotFound(){
        Mockito.when(userDaoImpl.getByEmail("user@email.com")).thenReturn(Optional.empty());
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getByEmail("user@email.com");
        assertFalse(optionalUserEntity.isPresent());
    }

    private Collection<UserEntity> getUserEntityCollection(){
        List<UserEntity> userEntities = new ArrayList<>(2);
        userEntities.add(UserEntityGenerator.getUserEntityGeneratedById(1L));
        userEntities.add(UserEntityGenerator.getUserEntityGeneratedById(2L));
        return userEntities;
    }
}
