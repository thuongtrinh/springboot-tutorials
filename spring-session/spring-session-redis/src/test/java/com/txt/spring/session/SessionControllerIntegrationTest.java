package com.txt.spring.session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import redis.clients.jedis.Jedis;
//import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.Set;

@SpringBootTest(classes = SessionWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SessionControllerIntegrationTest {

//    private static RedisServer redisServer;

    @LocalServerPort
    private int port;

    private static Jedis jedis;
    private static TestRestTemplate testRestTemplate;
    private static TestRestTemplate testRestTemplateWithAuth;

//    @BeforeTestClass
//    public static void startRedisServer() throws IOException {
//        redisServer = new RedisServer(6379);
//        redisServer.start();
//    }
//
//    @AfterTestClass
//    public static void stopRedisServer() {
//        redisServer.stop();
//    }

   @BeforeAll
    public static void clearRedisData() {
        testRestTemplate = new TestRestTemplate();
        testRestTemplateWithAuth = new TestRestTemplate("admin", "password");

        jedis = new Jedis("localhost", 6379);
        jedis.flushAll();
    }

    @Test
    public void testRedisIsEmpty() {
        Set<String> result = jedis.keys("*");
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void testUnauthenticatedCantAccess() {
        ResponseEntity<String> result = testRestTemplate.getForEntity(getTestUrl(), String.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

   @Test
    public void testRedisControlsSession() {
        ResponseEntity<String> result = testRestTemplateWithAuth.getForEntity(getTestUrl(), String.class);
        Assertions.assertEquals("hello admin", result.getBody()); // login worked

        Set<String> redisResult = jedis.keys("*");
        Assertions.assertTrue(redisResult.size() > 0); // redis is populated with session data

        String sessionCookie = result.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionCookie);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        result = testRestTemplate.exchange(getTestUrl(), HttpMethod.GET, httpEntity, String.class);
        Assertions.assertEquals("hello admin", result.getBody()); // access with session works worked

        jedis.flushAll(); // clear all keys in redis

        result = testRestTemplate.exchange(getTestUrl(), HttpMethod.GET, httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());// access denied after sessions are removed in redis
    }

    private String getTestUrl() {
        return "http://localhost:" + port;
    }
}