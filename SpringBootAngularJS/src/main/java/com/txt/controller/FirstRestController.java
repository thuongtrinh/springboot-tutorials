package com.txt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.txt.dto.User;
import com.txt.service.UserService;

@RestController
public class FirstRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching user with id " + id);
		User user = userService.findById(id);
		if(user == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder urCmpnntsBldr) {
		System.out.println("Creating User " + user.getUsername());
		if(userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getUsername() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(urCmpnntsBldr.path("/user/{id}").buildAndExpand(user.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);
		User currentUser = userService.findById(id);

		if(currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUsername(user.getUsername());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting user with id " + id);
		User currentUser = userService.findById(id);

		if(currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting all users");
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.OK);
	}

}

