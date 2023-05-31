package com.txt.dbsecurity.dao;

import java.util.List;

import com.txt.dbsecurity.entity.Article;

public interface IArticleDao {

	List<Article> getAllArticles();

	Article getArticleById(Integer articleId);

	void addArticle(Article article);

	void updateArticle(Article article);

	boolean deleteArticle(Integer articleId);

	boolean articleExists(String title, String category);

	public int maxArticleId();
}
