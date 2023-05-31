package com.txt.dbsecurity.repositories;

import java.util.List;

import com.txt.dbsecurity.entities.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRespository extends CrudRepository<Article, Long> {

    List<Article> findByTitle(String title);

    List<Article> findDistinctByCategory(String category);

    List<Article> findByTitleAndCategory(String title, String category);
}