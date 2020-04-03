package com.txt.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.txt.entity.Article;

import txt.com.mapper.ArticleRowMapper;

@Transactional
@Repository
public class ArticleDAO implements IArticleDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Article> getAllArticles() {
		String sql = "select * from articles";
		RowMapper<Article> rowMapper = new ArticleRowMapper();
		List<Article> list = this.jdbcTemplate.query(sql, rowMapper);
		return list;
	}

	@Override
	public Article getArticleById(long articleId) {
		
		String sql = "select * from articles where article_id = ?";
		Article article = null;

		//If table column name and java entity fields name are same, 
		//then we can directly use Spring JDBC BeanPropertyRowMapper to map a row with java object.
		RowMapper<Article> rowMapper = new ArticleRowMapper(); //new BeanPropertyRowMapper<>(Article.class);
		try{
			article = jdbcTemplate.queryForObject(sql, rowMapper, articleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public void addArticle(Article article) {
		String sql = "insert into articles(article_id, category, title) values (?, ?, ?)";
		jdbcTemplate.update(sql, article.getArticleId(), article.getCategory(), article.getTitle());

		// Fetch article id
		sql = "SELECT article_id FROM articles WHERE title = ? and category=?";
		int articleId = jdbcTemplate.queryForObject(sql, Integer.class, article.getTitle(), article.getCategory());

		// Set article id
		article.setArticleId(articleId);
	}

	@Override
	public void updateArticle(Article article) {
		String sql = "update articles set category = ?, title = ? where article_id = ?";
		jdbcTemplate.update(sql, article.getCategory(), article.getTitle(), article.getArticleId());
	}

	@Override
	public void deleteArticle(int articleId) {
		String sql = "delete from articles where article_id = ?";
		jdbcTemplate.update(sql, articleId);
	}

	@Override
	public boolean articleExists(String title, String category) {
		String sql = "select count(*) from articles where category = ? and title = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, category, title);

		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int maxArticleId() {
		String sql = "select max(article_id) from articles";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

}
