package com.txt.elasticsearch.controller;

import com.txt.elasticsearch.entities.User;
import com.txt.elasticsearch.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is to demo how ElasticsearchRepository can be used to Save/Retrieve/Delete
 */
@Tag(name = "User Repository API", description = "UserRepositoryController API")
@RestController
@RequestMapping("/user/repo")
@Slf4j
public class UserRepositoryController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @GetMapping("/id/{userId}")
    public User getUser(@PathVariable String userId) {
        log.info("Getting user with ID: {}", userId);
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            log.info("User with ID: {} is {}", userId, user);
            return user.get();
        }
        return null;
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addNewUsers(@RequestBody User user) {
        log.info("Adding user : {}", user);
        userRepository.save(user);
        log.info("Added user : {}", user);
        return user;
    }

    @GetMapping(value = "/settings/{userId}")
    public Object getAllUserSettings(@PathVariable String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isPresent()) {
            return user.get().getUserSettings();
        } else {
            return "User not found.";
        }
    }

    @GetMapping(value = "/settings/{userId}/{key}")
    public String getUserSetting(
            @PathVariable String userId, @PathVariable String key) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return user.get().getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(
            @PathVariable String userId,
            @PathVariable String key,
            @PathVariable String value) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            User userupd = user.get();
            userupd.getUserSettings().put(key, value);
            userRepository.save(userupd);
            return "Key added";
        } else {
            return "User not found.";
        }
    }
}
