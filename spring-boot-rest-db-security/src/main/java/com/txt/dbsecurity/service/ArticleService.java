package com.txt.dbsecurity.service;

import java.util.List;

import com.txt.dbsecurity.entities.Article;
import org.springframework.security.access.annotation.Secured;

public interface ArticleService {

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Article> getAllArticles();

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    Article getArticleById(int articleId);

    @Secured({"ROLE_ADMIN"})
    boolean addArticle(Article article);

    @Secured({"ROLE_ADMIN"})
    void updateArticle(Article article);

    @Secured({"ROLE_ADMIN"})
    void deleteArticle(int articleId);

}
