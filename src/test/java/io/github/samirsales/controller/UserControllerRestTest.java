package io.github.samirsales.controller;

import io.github.samirsales.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void verifyIfGetAllIsForbidden(){
        final HttpEntity<?> requestEntity = null;
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange("/users", HttpMethod.GET, requestEntity, String.class);
        Assert.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

//    @Test
//    public void verifyIfGetAllIsOk() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "its_value");
//
//        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> responseEntity = testRestTemplate
//                .exchange("/users", HttpMethod.GET, requestEntity, String.class);
//        Assert.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
//    }
}
