package com.txt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.entity.Article;
import com.txt.repository.ArticleRepository;
import com.txt.service.ArticleService;

@Controller
public class JpaRepositoryController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping("/jpaRepository")
	public String initJpaRepository(Model model) throws JsonProcessingException{
		return "jpaRepository";
	}

	@RequestMapping("/getAllArticles")
	public String getAllArticles(Model model) throws JsonProcessingException{
		List<Article> list = articleService.getAllArticles();

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByTitle")
	public String getListArticleByTitle(String title, Model model) throws JsonProcessingException{
		title = "Spring Boot Getting Started";
		List<Article> list = articleRepository.findByTitle(title);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByDistinctCategory")
	public String getListArticleByDistinctCategory(Model model) throws JsonProcessingException{
		String category = "Java";
		List<Article> list = articleRepository.findDistinctByCategory(category);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByIdGreaterThan")
	public String getListArticleByIdGreaterThan(Model model) throws JsonProcessingException{
		List<Article> list = articleRepository.findByArticleIdGreaterThan(3);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByIdLessThan")
	public String getListArticleByIdLessThan(Model model) throws JsonProcessingException{
		List<Article> list = articleRepository.findByArticleIdLessThan(5);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByIdBetween")
	public String getListArticleByIdBetween(Model model) throws JsonProcessingException{
		List<Article> list = articleRepository.findByArticleIdBetween(3, 5);

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByTitleNotNull")
	public String getListArticleByTitleNotNull(Model model) throws JsonProcessingException{
		List<Article> list = articleRepository.findByTitleNotNull();

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByTitleNull")
	public String getListArticleByTitleNull(Model model) throws JsonProcessingException{
		List<Article> list = articleRepository.findByTitleNull();

		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByTitleLike")
	public String getListArticleByTitleLike(Model model) throws JsonProcessingException{
		String title = "%Java%";
		List<Article> list = articleRepository.findByTitleLike(title);
		
		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticlesByTitleNot")
	public String getListArticleByTitleNot(Model model) throws JsonProcessingException{
		String title = "Java Core";
		List<Article> list = articleRepository.findByTitleNot(title);
		
		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticleDistinctArticleByTitleOrCategory")
	public String getListArticleDistinctArticleByTitleOrCategory(Model model) throws JsonProcessingException{
		String title = "Java Concurrency";
		String category = "Java";
		List<Article> list = articleRepository.findDistinctArticleByTitleOrCategory(title, category);
		
		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}

	@RequestMapping("/getArticleDistinctByTitleOrCategory")
	public String getListArticleDistinctByTitleOrCategory(Model model) throws JsonProcessingException{
		String title = "Spring Boot security";
		String category = "Spring Boot";
		List<Article> list = articleRepository.findArticleDistinctByTitleOrCategory(title, category);
		
		// pretty print
		ObjectMapper mapper = new ObjectMapper();
		String prettyArticles  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		model.addAttribute("articles", prettyArticles);
		return "jpaRepository";
	}
}
