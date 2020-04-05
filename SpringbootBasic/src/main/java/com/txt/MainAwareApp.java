package com.txt;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.txt.entity.User;
import com.txt.service.BeanUtilService;
import com.txt.service.UserService;

/**
 * @author Admin
 * ApplicationContextAware is an interface that allows access to Spring Bean, resource files.
 *
 */
public class MainAwareApp {

	public List<User> findAllUsers() {
		UserService userService = BeanUtilService.getBean(UserService.class);
		return userService.findAll();
	}

	public static void main(String [] args) {
		//Get encode of BCryptPasswordEncoder
		//admin: thuongtx - a123456: $2a$10$eLPcsMMNu8pGa0rKYDZiMOeTQSNYaSqf4pwnBTLFojE7VrZ9yCVV2
		//user:  tungtx   - b123456: $2a$10$bwBbZOQhHAIarbS1rkvq9OOAtypXozhZSRwGBLLNyb9BAnoAsXkPO
		//smith - alice - 123456:  $2a$10$4tHO9fcJwFISJ2T/xldeM.H2K8Osv9PF2C1l4FejxDvhO6guL/xhG

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("encoder: " + encoder.encode("123456"));
	}
}
