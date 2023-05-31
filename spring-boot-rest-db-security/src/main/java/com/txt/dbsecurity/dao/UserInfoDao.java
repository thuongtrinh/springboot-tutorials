package com.txt.dbsecurity.dao;

import com.txt.dbsecurity.entities.UserInfo;

public interface UserInfoDao {

	UserInfo getActiveUser(String userName);

}