package com.txt.dbsecurity.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.txt.dbsecurity.dao.UserInfoDao;
import com.txt.dbsecurity.entity.UserInfo;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDao.getActiveUser(username);
		UserDetails userDetails = null;
		
		if(userInfo != null){
			GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
			userDetails = new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
		}

		return userDetails;
	}

}
