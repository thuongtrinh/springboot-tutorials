package com.txt.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.txt.dao.IUserMvcInfoDao;
import com.txt.entity.UserMvcInfo;

@Service
public class MvcAppUserDetailService implements UserDetailsService {

	@Autowired
	private IUserMvcInfoDao userMvcInfoDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMvcInfo userMvcInfo = userMvcInfoDao.getUserInfo(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userMvcInfo.getRole());
		UserDetails userDetails = new User(userMvcInfo.getUserName(), userMvcInfo.getPassword(),
				Arrays.asList(authority));
		
		return userDetails;
	}
}
