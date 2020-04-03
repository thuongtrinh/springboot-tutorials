package com.txt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txt.dao.IArticleDAO;
import com.txt.entity.Article;

@Service
public class UserMvcInfoService implements IUserMvcInfoService {

	@Autowired
	private IArticleDAO articleDAO;

	@Override
	public List<Article> getAllUserArticles() {
		return articleDAO.getAllArticles();
	}
}
