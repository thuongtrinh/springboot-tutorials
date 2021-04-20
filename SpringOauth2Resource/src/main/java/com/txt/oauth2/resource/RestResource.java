package com.txt.oauth2.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestResource {

    @RequestMapping("/api/users/me")
    public ResponseEntity<UserProfile> profile() {
        //Build some dummy data to return for testing
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        String email = username + "@txt.com";

        UserProfile profile = new UserProfile();
        profile.setName(username);
        profile.setEmail(email);

        return ResponseEntity.ok(profile);
    }
}
