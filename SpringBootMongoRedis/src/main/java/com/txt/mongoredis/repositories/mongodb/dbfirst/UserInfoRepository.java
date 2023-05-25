package com.txt.mongoredis.repositories.mongodb.dbfirst;

import com.txt.mongoredis.entities.mongodb.dbfirst.UserInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, ObjectId>, QuerydslPredicateExecutor<UserInfo> {
}
