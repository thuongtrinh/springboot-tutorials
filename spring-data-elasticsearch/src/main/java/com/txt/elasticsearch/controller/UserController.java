package com.txt.elasticsearch.controller;

import com.txt.elasticsearch.dao.UserDAO;
import com.txt.elasticsearch.entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is to demo how ElasticsearchTemplate can be used to Save/Retrieve
 */
@Tag(name = "User NativeSearch API", description = "UserController API")
@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @GetMapping("/id/{userId}")
    public User getUser(@PathVariable String userId) {
        User user = userDAO.getUserById(userId);
        return user;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        userDAO.addNewUser(user);
        return user;
    }

    @RequestMapping(value = "/settings/{name}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String name) {
        return userDAO.getAllUserSettings(name);
    }

    @RequestMapping(value = "/settings/{name}/{key}", method = RequestMethod.GET)
    public String getUserSetting(
            @PathVariable String name, @PathVariable String key) {
        return userDAO.getUserSetting(name, key);
    }

    @RequestMapping(value = "/settings/{name}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(
            @PathVariable String name,
            @PathVariable String key,
            @PathVariable String value) {
        return userDAO.addUserSetting(name, key, value);
    }
}