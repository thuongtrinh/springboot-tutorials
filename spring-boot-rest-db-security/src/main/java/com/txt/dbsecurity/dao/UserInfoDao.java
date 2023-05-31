package com.txt.dbsecurity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.txt.dbsecurity.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserInfoDao implements IUserInfoDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo getActiveUser(String userName) {
		UserInfo userInfo = null;
		short enabled = 1;
		String hql = "select u from UserInfo u where userName = ?1 and enabled = ?2";
		List<UserInfo> users = entityManager.createQuery(hql)
				.setParameter(1, userName).setParameter(2, enabled).getResultList();
		
		if(users != null && users.size() > 0){
			userInfo = users.get(0);
		}

		return userInfo;
	}

}
