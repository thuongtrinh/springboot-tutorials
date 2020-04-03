package com.txt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txt.entity.Article;
import com.txt.repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> getAllArticles() {
		List<Article> list = new ArrayList<Article>();
		articleRepository.findAll().forEach(article -> list.add(article));;
		return list;
	}

	@Override
	public Article getArticleById(long articleId) {
		return articleRepository.findById(articleId).get();
	}

	@Override
	public synchronized boolean addArticle(Article article) {
		if(article != null){
			if(articleRepository.findById(article.getArticleId()).get() == null){
				articleRepository.save(article);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void updateArticle(Article article) {
		articleRepository.save(article);
	}

	@Override
	public void deleteArticle(int articleId) {
		articleRepository.delete(this.getArticleById(articleId));
	}
}
