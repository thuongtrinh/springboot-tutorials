package com.txt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.txt.entity.Article;

public interface ArticleRespository extends CrudRepository<Article, Long> {

	List<Article> findByTitle(String title);

	List<Article> findDistinctByCategory(String category);

	List<Article> findByTitleAndCategory(String title, String category);
}