package com.txt.dao;

import com.txt.entity.UserInfo;

public interface IUserInfoDao {

	UserInfo getActiveUser(String userName);

}