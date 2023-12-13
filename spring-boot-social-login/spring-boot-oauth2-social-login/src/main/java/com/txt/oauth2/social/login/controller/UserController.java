package com.txt.oauth2.social.login.controller;

import com.txt.oauth2.social.login.config.security.UserPrincipal;
import com.txt.oauth2.social.login.entity.authen.User;
import com.txt.oauth2.social.login.exception.ResourceNotFoundException;
import com.txt.oauth2.social.login.repository.UserRepository;
import com.txt.oauth2.social.login.validation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
