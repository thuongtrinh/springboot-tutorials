package com.txt.dbsecurity.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "article", namespace = "com.txt")
public class ArticleXml {

	@JacksonXmlProperty(localName = "article_id")
	private Integer articleId;
	@JacksonXmlProperty(localName = "title")
	private String title;
	@JacksonXmlProperty(localName = "category")
	private String category;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
