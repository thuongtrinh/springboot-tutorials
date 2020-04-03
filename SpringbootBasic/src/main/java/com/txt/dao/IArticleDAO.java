package com.txt.dao;

import java.util.List;

import com.txt.entity.Article;

public interface IArticleDAO {

	List<Article> getAllArticles();

	Article getArticleById(long articleId);

	void addArticle(Article article);

	void updateArticle(Article article);

	void deleteArticle(int articleId);

	boolean articleExists(String title, String category);

	int maxArticleId();
}
