package io.github.samirsales.dao;

import io.github.samirsales.model.entity.ImageEntity;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.model.enums.Gender;
import io.github.samirsales.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@SuppressWarnings("WeakerAccess")
public class UserDaoImplTest {

    private UserDaoImplMock userDaoImplMock = null;

    @Before
    public void setUp(){
        userDaoImplMock = new UserDaoImplMock();
    }

    @Test
    public void testGetAll(){
        Mockito.when(userDaoImplMock.getAll()).thenReturn(getUserEntityCollection());
        Collection<UserEntity> collection = userDaoImplMock.getAll();
        assertEquals(2, collection.size());
    }

    @Test
    public void testGetAllWithoutEntities(){
        Mockito.when(userDaoImplMock.getAll()).thenReturn(new ArrayList<>());
        Collection<UserEntity> collection = userDaoImplMock.getAll();
        assertEquals(0, collection.size());
    }

    @Test
    public void testGetById(){
        Mockito.when(userDaoImplMock.getById(1L)).thenReturn(Optional.of(getUserEntity1()));
        Optional<UserEntity> optionalUserEntity = userDaoImplMock.getById(1L);
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetByIdNotFound(){
        Mockito.when(userDaoImplMock.getById(1L)).thenReturn(Optional.empty());
        Optional<UserEntity> optionalUserEntity = userDaoImplMock.getById(1L);
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByLogin(){
        Mockito.when(userDaoImplMock.getByLogin("username1")).thenReturn(Optional.of(getUserEntity1()));
        Optional<UserEntity> optionalUserEntity = userDaoImplMock.getByLogin("username1");
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByLoginNotFound(){
        Mockito.when(userDaoImplMock.getByLogin("username1")).thenReturn(Optional.empty());
        Optional<UserEntity> optionalUserEntity = userDaoImplMock.getByLogin("username1");
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByEmail(){
        Mockito.when(userDaoImplMock.getByEmail("user@email.com")).thenReturn(Optional.of(getUserEntity1()));
        Optional<UserEntity> optionalUserEntity = userDaoImplMock.getByEmail("user@email.com");
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void testGetActiveByEmailNotFound(){
        Mockito.when(userDaoImplMock.getByEmail("user@email.com")).thenReturn(Optional.empty());
        Optional<UserEntity> optionalUserEntity = userDaoImplMock.getByEmail("user@email.com");
        assertFalse(optionalUserEntity.isPresent());
    }

    public class UserDaoImplMock extends UserDaoImpl{
        public UserDaoImplMock() {
            userRepository = Mockito.mock(UserRepository.class);
        }
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
