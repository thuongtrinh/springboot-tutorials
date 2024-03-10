package com.txt.wssoap.producer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.txt.wssoap.producer.repository.ArticleRepository;
import com.txt.wssoap.producer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.txt.wssoap.producer.entity.Article;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article getArticleById(long articleId) {
        Article obj = articleRepository.findByArticleId(articleId);
        return obj;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> list = new ArrayList<>();
        articleRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    @Modifying
    public synchronized boolean addArticle(Article article) {
        List<Article> list = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory());
        if (list.size() > 0) {
            return false;
        } else {
            article = articleRepository.save(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
