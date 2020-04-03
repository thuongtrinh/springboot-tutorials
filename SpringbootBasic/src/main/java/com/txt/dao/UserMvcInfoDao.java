package com.txt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.txt.entity.UserMvcInfo;

@Repository
public class UserMvcInfoDao implements IUserMvcInfoDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserMvcInfo getUserInfo(String username) {
		UserMvcInfo userMvcInfo = new UserMvcInfo();
		short enable = 1;
		 List<?> list = entityManager.createQuery("SELECT u FROM UserMvcInfo u WHERE userName=?1 and enabled=?2")
			.setParameter(1, username)
			.setParameter(2, enable)
			.getResultList();
		
		if(list != null && list.size() > 0){
			userMvcInfo = (UserMvcInfo) list.get(0);
		}
		
		return userMvcInfo;
	}

}
