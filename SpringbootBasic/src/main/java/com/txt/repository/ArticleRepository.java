package com.txt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.txt.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

	List<Article> findDistinctByCategory(String category);

	List<Article> findByTitle(String title);

	List<Article> findByTitleAndCategory(String title, String category);

	List<Article> findByArticleIdGreaterThan(long id);

	List<Article> findByArticleIdLessThan(long id);

	List<Article> findByArticleIdBetween(long fromId, long toId);

	List<Article> findByTitleNotNull();

	List<Article> findByTitleNull();

	List<Article> findByTitleLike(String title);

	List<Article> findByTitleNot(String title);

	// Enables the distinct flag for the query
	List<Article> findDistinctArticleByTitleOrCategory(String title, String category);
	List<Article> findDistinctArticleByTitleLikeOrCategory(String title, String category);

	List<Article> findArticleDistinctByTitleOrCategory(String title, String category);

	// Enabling ignoring case for an individual property
	List<Article> findByTitleIgnoreCase(String title);

	// Enabling ignoring case for all suitable properties
	List<Article> findByTitleAndCategoryAllIgnoreCase(String title, String category);

	// Enabling static ORDER BY for a query
	List<Article> findByTitleOrderByTitleAsc(String title);

	List<Article> findByTitleOrderByCategoryDesc(String category);
}
