package com.txt.repository;

import org.springframework.data.repository.CrudRepository;

import com.txt.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
