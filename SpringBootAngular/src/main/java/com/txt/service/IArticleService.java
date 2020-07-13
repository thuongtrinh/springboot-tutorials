package com.txt.service;

import java.util.List;

import com.txt.entity.Article;

public interface IArticleService {

	List<Article> getAllArticles();

	Article getArticleById(int articleId);

	boolean createArticle(Article article);

	void updateArticle(Article article);

	void deleteArticle(int articleId);
}
