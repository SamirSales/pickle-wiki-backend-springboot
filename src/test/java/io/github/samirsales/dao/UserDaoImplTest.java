package io.github.samirsales.dao;

import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;
import io.github.samirsales.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@SuppressWarnings("WeakerAccess")
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
        Mockito.when(userDaoImpl.getById(1L)).thenReturn(Optional.of(getUserEntity1()));
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
        Mockito.when(userDaoImpl.getByLogin("username1")).thenReturn(Optional.of(getUserEntity1()));
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getByLogin("username1");
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
        Mockito.when(userDaoImpl.getByEmail("user@email.com")).thenReturn(Optional.of(getUserEntity1()));
        Optional<UserEntity> optionalUserEntity = userDaoImpl.getByEmail("user@email.com");
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
        userEntities.add(getUserEntity1());
        userEntities.add(getUserEntity2());
        return userEntities;
    }

    private UserEntity getUserEntity1(){
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

    private UserEntity getUserEntity2(){
        Long id = 2L;
        String name = "Name2";
        String username = "username2";
        String email = "user2@email.com";
        final boolean isActive = true;
        String password = "123456789";
        Gender gender = Gender.MALE;
        Set<RoleEntity> roleEntities = new HashSet<>();
        final ImageEntity imageProfile = null;
        return new UserEntity(id, name, username, email,isActive, password, gender, roleEntities, imageProfile);
    }
}
