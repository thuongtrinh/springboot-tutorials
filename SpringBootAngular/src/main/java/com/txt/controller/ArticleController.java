package com.txt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.txt.entity.Article;
import com.txt.service.IArticleService;

@RestController
@RequestMapping(value = "user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@GetMapping("article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") String id) {
		Article article = articleService.getArticleById(Integer.parseInt(id));
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@GetMapping("all-articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		System.out.println("Get all-articles");
		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}

	@PostMapping("article")
	public ResponseEntity<Void> createArticle(@RequestBody Article article, UriComponentsBuilder builder) {
		boolean flag = articleService.createArticle(article);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article?id={id}").buildAndExpand(article.getArticleId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("article")
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	@DeleteMapping("article/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") String id) {
		System.out.println("deleteArticle: " + id);
		articleService.deleteArticle(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
