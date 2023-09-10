package com.txt.elasticsearch.dao;

import com.txt.elasticsearch.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(String userId);

    User addNewUser(User user);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);
}