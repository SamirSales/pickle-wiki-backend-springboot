package io.github.samirsales.controller;

import io.github.samirsales.model.dto.CredentialDTO;
import io.github.samirsales.model.dto.UserDTO;
import io.github.samirsales.model.entity.UserEntity;
import io.github.samirsales.repository.UserRepository;
import io.github.samirsales.utils.UserEntityGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerRestTest {

    @Autowired
    @SuppressWarnings("unused")
    private TestRestTemplate testRestTemplate;

    @Autowired
    @SuppressWarnings("unused")
    private static UserRepository userRepository;

    private final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final String USER_CONTROLLER_URL_PREFIX = "/users";

    @AfterClass
    public static void reset(){
        if(userRepository != null){
            userRepository.deleteAll();
        }
    }

    @Test
    public void verifyIfGetAllIsForbidden() {
        final HttpEntity<?> requestEntity = null;
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange(USER_CONTROLLER_URL_PREFIX, HttpMethod.GET, requestEntity, String.class);
        Assert.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void verifyIfGetAllIsOk() {
        HttpEntity<?> requestEntity = getHttpEntityWithAuthorization();
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange(USER_CONTROLLER_URL_PREFIX, HttpMethod.GET, requestEntity, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void verifyIfGetUserByIdWorks() {
        HttpEntity<?> requestEntity = getHttpEntityWithAuthorization();
        Long id = 1L;
        ResponseEntity<UserDTO> responseEntity = testRestTemplate
                .exchange(USER_CONTROLLER_URL_PREFIX + "/" + id , HttpMethod.GET, requestEntity, UserDTO.class);
        
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
    }

    @Test
    public void verifyIfGetUserByIdNotFound() {
        HttpEntity<?> requestEntity = getHttpEntityWithAuthorization();
        Long id = 999L;
        ResponseEntity<UserDTO> responseEntity = testRestTemplate
                .exchange(USER_CONTROLLER_URL_PREFIX + "/" + id , HttpMethod.GET, requestEntity, UserDTO.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assert.assertNull(requestEntity.getBody());
    }

    @Test
    public void verifyIfInsertUserWorks() {
        UserEntity userEntity = UserEntityGenerator.getUserEntityGeneratedById(3L);
        UserEntity userEntityToInsert = userEntity.toBuilder().id(null).build();
        UserDTO userDTOToInsert = new UserDTO(userEntityToInsert);
        UserDTO userDTOWithSetPassword = userDTOToInsert.toBuilder().password("123456").build();

        String authenticationToken = getAuthenticationToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER_NAME, authenticationToken);

        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userDTOWithSetPassword, headers);
        ResponseEntity<UserDTO> responseEntity = testRestTemplate
            .exchange(USER_CONTROLLER_URL_PREFIX + "/" , HttpMethod.POST, requestEntity, UserDTO.class);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNull(responseEntity.getBody());
    }

    private HttpEntity<?> getHttpEntityWithAuthorization(){
        String authenticationToken = getAuthenticationToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER_NAME, authenticationToken);
        return new HttpEntity<>(headers);
    }

    private String getAuthenticationToken() {
        ResponseEntity<String> responseEntityAuthentication = getResponseEntityAuthentication();
        return responseEntityAuthentication.getHeaders().getFirst(AUTHORIZATION_HEADER_NAME);
    }

    private ResponseEntity<String> getResponseEntityAuthentication() {
        CredentialDTO credentialDTO = getAdminCredentialDTO();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CredentialDTO> requestEntity = new HttpEntity<>(credentialDTO, headers);
        return testRestTemplate.exchange("/login", HttpMethod.POST, requestEntity, String.class);
    }

    private CredentialDTO getAdminCredentialDTO(){
        String username = "admin";
        String password = "admin";
        return new CredentialDTO(username, password);
    }
}
