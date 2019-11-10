package io.github.samirsales.repository;

import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.utils.UserEntityGenerator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    @SuppressWarnings("unused")
    private UserRepository userRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public  void end(){
        userRepository.deleteAll();
    }

    @Test
    public void findByUsernameTest(){
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        userRepository.save(userEntity);

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userEntity.getUsername());
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    public void findByUsernameWithNoFoundResultTest(){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername("username_not_found");
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    public void insertUserWithNullName(){
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("The 'name' attribute must be filled.");

        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        UserEntity userEntityWithNullName = userEntity.toBuilder().name(null).build();
        userRepository.save(userEntityWithNullName);
    }

    @Test
    public void insertUserWithNullUsername(){
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("The 'username' attribute must be filled.");

        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        UserEntity userEntityWithNullName = userEntity.toBuilder().username(null).build();
        userRepository.save(userEntityWithNullName);
    }

    @Test
    public void insertUserWithNullPassword(){
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("The 'password' attribute must be filled.");

        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        UserEntity userEntityWithNullName = userEntity.toBuilder().password(null).build();
        userRepository.save(userEntityWithNullName);
    }
}
