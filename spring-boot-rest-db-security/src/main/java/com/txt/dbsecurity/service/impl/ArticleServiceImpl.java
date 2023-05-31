package com.txt.dbsecurity.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.txt.dbsecurity.dao.ArticleDao;
import com.txt.dbsecurity.entities.Article;
import com.txt.dbsecurity.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txt.dbsecurity.repositories.ArticleRespository;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDAO;

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

}
