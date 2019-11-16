package io.github.samirsales.controller;

import io.github.samirsales.model.dto.CredentialDTO;
import io.github.samirsales.repository.UserRepository;
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
    private UserRepository userRepository;

    @Test
    public void verifyIfGetAllIsForbidden() {
        final HttpEntity<?> requestEntity = null;
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange("/users", HttpMethod.GET, requestEntity, String.class);
        Assert.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void verifyIfGetAllIsOk() {
        String authenticationToken = getAuthenticationToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authenticationToken);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange("/users", HttpMethod.GET, requestEntity, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private String getAuthenticationToken() {
        ResponseEntity<String> responseEntityAuthentication = getResponseEntityAuthentication();
        return responseEntityAuthentication.getHeaders().getFirst("Authorization");
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
