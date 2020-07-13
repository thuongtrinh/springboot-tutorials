package com.txt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.txt.entity.Article;

@Transactional
@Repository
public class ArticleDAO implements IArticleDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class, articleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		String hql = "FROM Article as atcl ORDER BY atcl.articleId DESC";
		return (List<Article>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void createArticle(Article article) {
		entityManager.persist(article);
	}

	@Override
	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}

	@Override
	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}

	@Override
	public boolean articleExists(String title, String category) {
		String hql = "FROM Article as atcl WHERE atcl.title = ?1 and atcl.category = ?2";
		int count = entityManager.createQuery(hql)
				.setParameter(1, title)
				.setParameter(2, category)
				.getResultList().size();
		
		System.out.println("count exist: " + count);
		return count > 0 ? true : false;
	}

	@Override
	public int maxArticleId() {
		String hql = "select COALESCE(max(articleId), 0) from Article ";
		int count = (int) entityManager.createQuery(hql).getSingleResult();
		return count;
	}
}
