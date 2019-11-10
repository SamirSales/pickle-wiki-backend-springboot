package io.github.samirsales.repository;

import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.utils.UserEntityGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
