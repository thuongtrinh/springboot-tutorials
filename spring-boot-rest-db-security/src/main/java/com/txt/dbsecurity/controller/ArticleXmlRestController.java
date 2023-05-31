package com.txt.dbsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.txt.dbsecurity.entity.Article;
import com.txt.dbsecurity.entity.ArticleXml;
import com.txt.dbsecurity.service.IArticleService;

@RestController
@RequestMapping(value = "userXml")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ArticleXmlRestController {

	@Autowired
	private IArticleService articleService;

	@GetMapping(value = "article/{id}", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ArticleXml> getArticleById(@PathVariable("id") Integer id) {
		ArticleXml article = new ArticleXml();
		BeanUtils.copyProperties(articleService.getArticleById(id), article);
		return new ResponseEntity<ArticleXml>(article, HttpStatus.OK);
	}

	@GetMapping(value = "articles", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ArticleXml>> getAllArticles() {
		List<ArticleXml> responseArticleList = new ArrayList<>();
		List<ArticleXml> articleList = articleService.getAllArticlesXml();
		for (int i = 0; i < articleList.size(); i++) {
			ArticleXml ob = new ArticleXml();
			BeanUtils.copyProperties(articleList.get(i), ob);
			responseArticleList.add(ob);
		}
		
		return new ResponseEntity<List<ArticleXml>>(responseArticleList, HttpStatus.OK);
	}

	// Creates a new article
	@PostMapping(value = "article", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> addArticle(@RequestBody ArticleXml articleXml, UriComponentsBuilder builder) {
		Article article = new Article();
		BeanUtils.copyProperties(articleXml, article);
		boolean flag = articleService.addArticle(article);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "article/{id}", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// Updates article
	@PutMapping(value = "article", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ArticleXml> updateArticle(@RequestBody ArticleXml articleXml) {
		Article article = new Article();
		BeanUtils.copyProperties(articleXml, article);
		articleService.updateArticle(article);

		ArticleXml ob = new ArticleXml();
		BeanUtils.copyProperties(article, ob);
		return new ResponseEntity<ArticleXml>(ob, HttpStatus.OK);
	}
}
