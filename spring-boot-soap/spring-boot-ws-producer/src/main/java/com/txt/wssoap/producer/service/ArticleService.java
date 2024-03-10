package com.txt.wssoap.producer.service;

import java.util.List;

import com.txt.wssoap.producer.entity.Article;

public interface ArticleService {

    List<Article> getAllArticles();

    Article getArticleById(long articleId);

    boolean addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Article article);
}
