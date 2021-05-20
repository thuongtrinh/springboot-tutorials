package com.txt.springsessionmongodb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.session.data.mongo.MongoIndexedSessionRepository;

import java.util.Base64;


@SpringBootTest(classes = SpringSessionMongoDBApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSessionMongoDBIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MongoIndexedSessionRepository repository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void givenEndpointIsCalledTwiceAndResponseIsReturned_whenMongoDBIsQueriedForCount_thenCountMustBeSame() {
        HttpEntity<String> response = restTemplate
                .exchange("http://localhost:" + port, HttpMethod.GET, null, String.class);
        HttpHeaders headers = response.getHeaders();
        String set_cookie = headers.getFirst(HttpHeaders.SET_COOKIE);

        Assertions.assertEquals(response.getBody(),
                repository.findById(getSessionId(set_cookie)).getAttribute("count").toString());
    }

    private String getSessionId(String cookie) {
        return new String(Base64.getDecoder().decode(cookie.split(";")[0].split("=")[1]));
    }

}
