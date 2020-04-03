package txt.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.txt.entity.Article;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		Article article = new Article();
		article.setArticleId(rs.getLong("article_id"));
		article.setCategory(rs.getString("category"));
		article.setTitle(rs.getString("title"));
		return article;
	}

}
