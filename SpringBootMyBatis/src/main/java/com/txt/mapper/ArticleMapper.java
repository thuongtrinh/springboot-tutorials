package com.txt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.txt.entity.Article;

@Mapper
public interface ArticleMapper {

	@Select("SELECT * from articles")
	public List<Article> findAll();

	@Select("SELECT COALESCE(max(article_id), 0) FROM articles")
	public int findMaxId();

	@Select("SELECT * FROM articles WHERE article_id = #{id}")
	public Article findById(long id);

	@Delete("DELETE FROM articles WHERE article_id = #{id}")
	public int deleteById(long id);

	@Insert("INSERT INTO articles(article_id, title, category) VALUES (#{article_id}, #{title}, #{category})")
	public int insert(Article article);

	@Update("UPDATE articles SET title = #{title}, category=#{category} WHERE article_id = #{article_id}")
	public int update(Article article);
}

