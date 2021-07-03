package com.txt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.txt.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	Article findByArticleId(Long articleId);

	List<Article> findByTitleAndCategory(String title, String category);
}
