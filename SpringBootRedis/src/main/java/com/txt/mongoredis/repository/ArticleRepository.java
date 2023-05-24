package com.txt.mongoredis.repository;

import com.txt.mongoredis.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
