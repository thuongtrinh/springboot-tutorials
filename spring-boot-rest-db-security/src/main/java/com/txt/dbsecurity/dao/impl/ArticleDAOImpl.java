package com.txt.dbsecurity.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.txt.dbsecurity.dao.ArticleDAO;
import org.springframework.stereotype.Repository;

import com.txt.dbsecurity.entities.Article;

@Repository
@Transactional
public class ArticleDAOImpl implements ArticleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Article> getAllArticles() {
        String hql = "select u from Article u order by articleId";
        List<Article> list = entityManager.createQuery(hql).getResultList();
        return list;
    }

    @Override
    public Article getArticleById(Integer articleId) {
        Article article = entityManager.find(Article.class, articleId);
        return article;
    }

    @Override
    public void addArticle(Article article) {
        int id = maxArticleId() + 1;
        //article.setArticleId(id);
        article.setTitle(article.getTitle() + id);
        article.setCategory(article.getCategory() + id);
        entityManager.persist(article);
    }

    @Override
    public void updateArticle(Article article) {
        Article articleUpd = getArticleById(article.getArticleId());
        if (articleUpd != null) {
            articleUpd.setTitle(article.getTitle());
            articleUpd.setCategory(article.getCategory());
            entityManager.flush();
        }
    }

    @Override
    public boolean deleteArticle(Integer articleId) {
        Article article = getArticleById(articleId);
        if (article != null) {
            entityManager.remove(article);
            return true;
        }

        return false;
    }

    @Override
    public boolean articleExists(String title, String category) {
        String hql = "FROM Article as atcl WHERE atcl.title = ?1 and atcl.category = ?2";
        int count = entityManager.createQuery(hql)
                .setParameter(1, title)
                .setParameter(2, category)
                .getResultList().size();

        return count > 0 ? true : false;
    }

    @Override
    public int maxArticleId() {
        String hql = "select COALESCE(max(articleId), 0) from Article ";
        int count = (int) entityManager.createQuery(hql).getSingleResult();
        return count;
    }
}
