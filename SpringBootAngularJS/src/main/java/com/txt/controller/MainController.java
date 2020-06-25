package com.txt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String userManagement(Model model) {
		return "userManagement";
	}
}
