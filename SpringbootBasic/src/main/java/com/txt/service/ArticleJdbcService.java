package com.txt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txt.dao.IArticleDAO;
import com.txt.entity.Article;

@Service
public class ArticleJdbcService implements IArticleJdbcService {

	@Autowired
	private IArticleDAO articleDAO;

	@Override
	public List<Article> getAllArticles() {
		return articleDAO.getAllArticles();
	}

	@Override
	public Article getArticleById(long articleId) {
		return articleDAO.getArticleById(articleId);
	}

	@Override
	public boolean addArticle(Article article) {
		if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
			return false;
		} else {
			articleDAO.addArticle(article);
			return true;
		}
	}

	@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
	}

	@Override
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticle(articleId);
	}

	@Override
	public int maxArticleId() {
		return articleDAO.maxArticleId();
	}
}
