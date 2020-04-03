package com.txt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.entity.Article;
import com.txt.service.IArticleJdbcService;

@Controller
public class JDBCController {

	@Autowired
	private IArticleJdbcService articleJdbcService;

	@RequestMapping("/springbootJdbc")
	public String initSpringBootJdbc(Model model) throws JsonProcessingException{
		return "springBootJdbc";
	}

	@RequestMapping("/jdbcGetAllArticles")
	public String getAllArticles(Model model) throws JsonProcessingException{
		List<Article> list = articleJdbcService.getAllArticles();

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "springBootJdbc";
	}

	@RequestMapping("/jdbcGetArticlesById")
	public String getAllArticlesByTitle(Model model) throws JsonProcessingException{
		Article article = articleJdbcService.getArticleById(2);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(article);
		model.addAttribute("articles", prettyArticles);
		return "springBootJdbc";
	}

	@RequestMapping("/jdbcAddArticle")
	public String addArticle(Model model) throws JsonProcessingException{
		int maxId = articleJdbcService.maxArticleId();
		maxId += 1;
		Article article = new Article();
		article.setArticleId(maxId);
		article.setCategory("category" + maxId);
		article.setTitle("title" + maxId);
		boolean result = articleJdbcService.addArticle(article);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
		model.addAttribute("articles", prettyArticles);
		return "springBootJdbc";
	}

	@RequestMapping("/jdbcUpdateArticle")
	public String updateArticle(Model model) throws JsonProcessingException{
		Article article = articleJdbcService.getArticleById(8);
		boolean result = false;
		if(article != null){
			String text = "-Upd";
			article.setCategory(article.getCategory() + text);
			article.setTitle(article.getTitle() + text);
			articleJdbcService.updateArticle(article);
			result = true;
		}

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
		model.addAttribute("articles", prettyArticles);
		return "springBootJdbc";
	}

	@RequestMapping("/jdbcDeleteArticle/{id}")
	public String deleteArticle(@PathVariable int id, Model model) throws JsonProcessingException{
		Article article = articleJdbcService.getArticleById(id);
		boolean result = false;
		if(article != null){
			articleJdbcService.deleteArticle(id);
			result = true;
		}

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
		model.addAttribute("articles", prettyArticles);
		return "springBootJdbc";
	}
}
