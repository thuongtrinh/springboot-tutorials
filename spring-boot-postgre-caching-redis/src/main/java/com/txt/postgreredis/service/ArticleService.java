package com.txt.postgreredis.service;

import java.util.List;

import com.txt.postgreredis.entity.Article;

public interface ArticleService {

	List<Article> getAllArticles();

	Article getArticleById(long articleId);

	Article addArticle(Article article);

	Article updateArticle(Article article);

	void deleteArticle(long articleId);
}
