package com.txt.dbsecurity.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.txt.dbsecurity.entities.Article;
import com.txt.dbsecurity.service.ArticleService;

@RestController
@Tag(name = "Rest Article API", description = "Article API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api")
public class ArticleController {

    final ArticleService articleService;

    @GetMapping("article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {
        log.info("Process getArticleById - {}", id);
        try {
            Article article = articleService.getArticleById(id);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Process getArticleById encounter errors: {}", ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            MDC.clear();
        }
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        log.info("Process getAllArticles");
        try {
            List<Article> list = articleService.getAllArticles();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Process getAllArticles encounter errors: {}", ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            MDC.clear();
        }
    }

    @PostMapping("articles")
    public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
        log.info("Process addArticle - {}", article);
        try {
            boolean flag = articleService.addArticle(article);
            if (flag == false) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("article/{id}").buildAndExpand(article.getArticleId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Process addArticle encounter errors: {}", ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            MDC.clear();
        }
    }

    @DeleteMapping("article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        log.info("Process deleteArticle byId- {}", id);
        try {
            articleService.deleteArticle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error("Process deleteArticle byId encounter errors: {}", ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            MDC.clear();
        }
    }

    @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        log.info("Process updateArticle - {}", article);
        try {
            articleService.updateArticle(article);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Process updateArticle encounter errors: {}", ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            MDC.clear();
        }
    }
}
