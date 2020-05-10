package com.txt.entity;

public class Article {

	private int article_id;
	private String title;
	private String category;

	public Article() {
	}

	public Article(String title, String category) {
		this.title = title;
		this.category = category;
	}

	public Article(int article_id, String title, String category) {
		this.article_id = article_id;
		this.title = title;
		this.category = category;
	}


	public long getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
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

	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", title=" + title + ", category=" + category + "]";
	}

}
