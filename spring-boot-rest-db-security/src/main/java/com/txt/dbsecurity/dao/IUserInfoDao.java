package com.txt.dbsecurity.dao;

import com.txt.dbsecurity.entity.UserInfo;

public interface IUserInfoDao {

	UserInfo getActiveUser(String userName);

}