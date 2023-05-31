package com.txt.dbsecurity.dao;


import com.txt.dbsecurity.entities.Article;

import java.util.List;

public interface ArticleDAO {

    List<Article> getAllArticles();

    Article getArticleById(Integer articleId);

    void addArticle(Article article);

    void updateArticle(Article article);

    boolean deleteArticle(Integer articleId);

    boolean articleExists(String title, String category);

    int maxArticleId();
}
