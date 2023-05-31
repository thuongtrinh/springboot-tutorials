package com.txt.dbsecurity.service;

import java.util.ArrayList;
import java.util.List;

import com.txt.dbsecurity.dao.IArticleDao;
import com.txt.dbsecurity.entity.Article;
import com.txt.dbsecurity.entity.ArticleXml;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txt.dbsecurity.repository.ArticleRespository;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private IArticleDao articleDAO;

	@Autowired
	private ArticleRespository articleRepository;

	@Override
	public Article getArticleById(int articleId) {
		Article obj = articleDAO.getArticleById(articleId);
		return obj;
	}

	@Override
	public List<Article> getAllArticles() {
		return articleDAO.getAllArticles();
	}

	@Override
	public synchronized boolean addArticle(Article article) {
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
	public List<ArticleXml> getAllArticlesXml() {
		List<ArticleXml> list = new ArrayList<>();
		articleRepository.findAll().forEach(e -> {
			ArticleXml articleXml = new ArticleXml();
			BeanUtils.copyProperties(e, articleXml);
			list.add(articleXml);
		});

		return list;
	}
}
