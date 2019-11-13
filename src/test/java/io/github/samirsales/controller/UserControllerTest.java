package io.github.samirsales.controller;

import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.repository.UserRepository;
import io.github.samirsales.utils.UserEntityGenerator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    @SuppressWarnings("unused")
    private UserRepository userRepository;

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mockMvc;


    @After
    public void reset(){
        userRepository.deleteAll();
    }

    @Test
    public void verifyIfGetAllIsForbidden() throws Exception {
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(1L);
        userRepository.save(userEntity);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users/"));
        resultActions.andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
