package com.txt.service;

import java.util.List;

import com.txt.entity.Article;

public interface IArticleService {

	List<Article> getAllArticles();

	Article getArticleById(long articleId);

	Article addArticle(Article article);

	Article updateArticle(Article article);

	void deleteArticle(long articleId);
}
