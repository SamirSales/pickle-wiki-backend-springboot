package io.github.samirsales.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.samirsales.model.dto.CredentialDTO;
import io.github.samirsales.repository.UserRepository;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerMvcTest {

    @Autowired
    @SuppressWarnings("unused")
    private static UserRepository userRepository;

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mockMvc;

    @AfterClass
    public static void reset(){
        if(userRepository != null){
            userRepository.deleteAll();
        }
    }

    @Test
    public void verifyIfGetAllIsForbidden() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users/"));
        resultActions.andExpect(status().isForbidden());
    }

    @Test
    public void verifyIfGetAllIsOk() throws Exception {
        String authenticationToken = getAuthenticationToken();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users/")
            .header(HttpHeaders.AUTHORIZATION, authenticationToken));
        resultActions.andExpect(status().isOk());
    }

    private String getAuthenticationToken() throws Exception {
        ResultActions authenticationResultAction = getAuthenticationResultAction();
        return authenticationResultAction.andReturn().getResponse().getHeader("Authorization");
    }

    private ResultActions getAuthenticationResultAction() throws Exception{
        CredentialDTO credentialDTO = getAdminCredentialDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String credentialDtoStringJson = objectMapper.writeValueAsString(credentialDTO);

        return mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(credentialDtoStringJson));
    }

    private CredentialDTO getAdminCredentialDTO(){
        String username = "admin";
        String password = "admin";
        return new CredentialDTO(username, password);
    }

}
