package com.txt.dbsecurity;

import java.net.URI;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.txt.dbsecurity.entities.Article;

public class RestClientTest {

    private HttpHeaders getHeaders() {
        String credential = "thuongtx:123456";
        String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
//        String encode = new String(Base64.decodeBase64(encodedCredential));
//        System.out.println(encode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + encodedCredential);
        return headers; // [Content-Type:"application/json", Authorization:"Basic bXVrZXNoOm0xMjM="]
    }

    public void getArticleByIdDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article/{id}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article.class, 1);
        Article article = responseEntity.getBody();

        System.out.println("Id:" + article.getArticleId()
                + ", Title:" + article.getTitle() + ", Category:" + article.getCategory());
    }

    public void getAllArticlesDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/articles";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article[].class);
        Article[] articles = responseEntity.getBody();

        for (Article article : articles) {
            System.out.println("Id:" + article.getArticleId()
                    + ", Title:" + article.getTitle() + ", Category: " + article.getCategory());
        }
    }

    public void addArticleDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article";

        Article objArticle = new Article();
        objArticle.setTitle("Spring REST Security using Hibernate");
        objArticle.setCategory("Spring");
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }

    public void updateArticleDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article";
        Article objArticle = new Article();
        objArticle.setArticleId(1);
        objArticle.setTitle("Update:Java Concurrency");
        objArticle.setCategory("Java");
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        restTemplate.put(url, requestEntity);
    }

    public void deleteArticleDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
    }

    public static void main(String args[]) {
        RestClientTest util = new RestClientTest();

//        util.getArticleByIdDemo();
        util.getAllArticlesDemo();
//        util.addArticleDemo();
//        util.updateArticleDemo();
//        util.deleteArticleDemo();
    }
}
