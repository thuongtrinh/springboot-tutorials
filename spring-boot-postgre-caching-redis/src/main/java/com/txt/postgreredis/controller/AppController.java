package com.txt.postgreredis.controller;

import java.util.List;

import com.txt.postgreredis.entity.Article;
import com.txt.postgreredis.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
@Tag(name = "AppController", description = "AppController")
public class AppController {

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "Get article by id")
    @GetMapping("article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @Operation(summary = "Get article list")
    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = articleService.getAllArticles();
        return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
    }

    @Operation(summary = "Add new article")
    @PostMapping("article")
    public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
        Article savedArticle = articleService.addArticle(article);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(savedArticle.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Update article")
    @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }

    @Operation(summary = "Delete article")
    @DeleteMapping("article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
