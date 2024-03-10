package com.txt.wssoap.consumer;

import com.txt.wssoap.consumer.client.ArticleClient;
import com.txt.wssoap.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSoapClientApplicationClient {

    public static void main(String[] args) {
        SpringApplication.run(SpringSoapClientApplicationClient.class, args);
    }

    @Bean
    CommandLineRunner lookup(ArticleClient articleClient) {
        return args -> {
            System.out.println("--- Get Article by Id ---");
            GetArticleByIdResponse articleByIdResponse = articleClient.getArticleById(1);
            ArticleInfo articleInfo = articleByIdResponse.getArticleInfo();
            System.out.println(articleInfo.getArticleId() + ", " + articleInfo.getTitle() + ", " + articleInfo.getCategory());

            System.out.println("--- Get all Articles ---");
            GetAllArticlesResponse allArticlesResponse = articleClient.getAllArticles();
            allArticlesResponse.getArticleInfo().stream()
                    .forEach(e -> System.out.println(e.getArticleId() + ", " + e.getTitle() + ", " + e.getCategory()));

            System.out.println("--- Add Article ---");
            String title = "Spring cloud";
            String category = "microservice";
            AddArticleResponse addArticleResponse = articleClient.addArticle(title, category);
            articleInfo = addArticleResponse.getArticleInfo();
            if (articleInfo != null) {
                System.out.println(articleInfo.getArticleId() + ", " + articleInfo.getTitle() + ", " + articleInfo.getCategory());
            }
            ServiceStatus serviceStatus = addArticleResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode() + ", Message: " + serviceStatus.getMessage());

            System.out.println("--- Update Article ---");
            articleInfo = new ArticleInfo();
            articleInfo.setArticleId(1);
            articleInfo.setTitle("Update:Java Concurrency");
            articleInfo.setCategory("Java");
            UpdateArticleResponse updateArticleResponse = articleClient.updateArticle(articleInfo);
            serviceStatus = updateArticleResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode() + ", Message: " + serviceStatus.getMessage());

            System.out.println("--- Delete Article ---");
            long articleId = 3;
            DeleteArticleResponse deleteArticleResponse = articleClient.deleteArticle(articleId);
            serviceStatus = deleteArticleResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode() + ", Message: " + serviceStatus.getMessage());
        };
    }
}