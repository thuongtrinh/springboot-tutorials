package com.txt.dbsecurity.config;

import java.util.Arrays;
import java.util.Optional;

import com.txt.dbsecurity.dao.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.txt.dbsecurity.entities.UserInfo;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.getActiveUser(username);
        UserDetails userDetails = null;

        if (userInfo != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
            userDetails = new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
        }

        return Optional.ofNullable(userDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exists"));
    }
}
