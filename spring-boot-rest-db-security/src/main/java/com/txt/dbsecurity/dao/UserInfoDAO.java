package com.txt.dbsecurity.dao;

import com.txt.dbsecurity.entities.UserInfo;

public interface UserInfoDAO {

    UserInfo getActiveUser(String userName);

}