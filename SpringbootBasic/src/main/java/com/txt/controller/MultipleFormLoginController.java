package com.txt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MultipleFormLoginController {

	@RequestMapping(value = "/login1")
	public String login1(@RequestParam(required = false) String message, final Model model){
		if(message != null && !message.isEmpty()){
			if("error".equals(message)){
				model.addAttribute("message", "Login failed!");
			} else if("logout".equals(message)){
				model.addAttribute("message", "Logout!");
			}
		}
		
		return "login1";
	}

	@RequestMapping(value = "/login2")
	public String login2(@RequestParam(required = false) String message, final Model model){
		if(message != null && !message.isEmpty()){
			if("error".equals(message)){
				model.addAttribute("message", "Login failed!");
			} else if("logout".equals(message)){
				model.addAttribute("message", "Logout!");
			} else if("timeout".equals(message)){
				model.addAttribute("message", "Login timeout!");
			} else if("max_session".equals(message)){
				model.addAttribute("message", "This accout has been login from another device!");
			}
		}
		
		return "login2";
	}

	@RequestMapping("/admin/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/403")
	public String accessDenied403() {
		return "403";
	}
}
