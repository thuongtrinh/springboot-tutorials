package com.txt.postgreredis.repository;

import com.txt.postgreredis.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
