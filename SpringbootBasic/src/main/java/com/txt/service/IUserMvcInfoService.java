package com.txt.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.txt.entity.Article;

public interface IUserMvcInfoService {

	@Secured({ "ROLE_ADMIN" })
	List<Article> getAllUserArticles();
}
