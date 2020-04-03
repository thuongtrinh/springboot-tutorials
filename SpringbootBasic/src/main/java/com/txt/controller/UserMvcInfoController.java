package com.txt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.txt.entity.Article;
import com.txt.service.IUserMvcInfoService;

@Controller
@RequestMapping("app")
public class UserMvcInfoController {

	@Autowired
	private IUserMvcInfoService userMvcInfoService;

	@RequestMapping("loginMvc")
	public String login(Model model) {
		return "login-mvc";
	}

	@RequestMapping("secure/article-details")
	public String displayListUserArticles(Model model) {
		List<Article> list = userMvcInfoService.getAllUserArticles();
		model.addAttribute("articleList", list);
		return "article-list";
	}
}
