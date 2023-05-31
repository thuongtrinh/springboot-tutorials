package com.txt.mongoredis.repositories.mongodb.dao;

import com.txt.mongoredis.dto.UserInfoDTO;
import com.txt.mongoredis.dto.UserShortDTO;

import java.util.List;

public interface UserInfoDao {

    List<UserInfoDTO> getListUserInfo(String policyNumber);

    List<UserShortDTO> getListUserInfoGroupUserPhone();

}
