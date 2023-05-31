package com.txt.mongoredis.repositories.mongodb.dao.impl;

import com.txt.mongoredis.dto.UserInfoDTO;
import com.txt.mongoredis.dto.UserShortDTO;
import com.txt.mongoredis.entities.mongodb.dbfirst.UserInfo;
import com.txt.mongoredis.repositories.mongodb.dao.UserInfoDao;
import com.txt.mongoredis.utils.MongoConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class UserInfoDaoImpl implements UserInfoDao {

    @Qualifier("dbfirsttemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserInfoDTO> getListUserInfo(String phone) {
        Criteria criteria = Criteria.where("phone").is(phone)
                .and("enabled").is(Boolean.TRUE);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria)
        );

        log.info("getListUserInfo:: query using phone {}: {}", phone, aggregation);
        AggregationResults<UserInfoDTO> groupResults = mongoTemplate
                .aggregate(aggregation, "user_info", UserInfoDTO.class);

        System.out.println(MongoConstants.getCollectionName(UserInfo.class));

        return groupResults.getMappedResults();
    }

    public List<UserShortDTO> getListUserInfoGroupUserPhone() {
        Criteria criteria = Criteria.where("enabled").is(Boolean.TRUE);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project()
                        .and("userName").as("username")
                        .and("phone").as("phone")
        );
        log.info("getListUserInfoGroupUserPhone:: query using: {}", aggregation);
        AggregationResults<UserShortDTO> groupResults = mongoTemplate
                .aggregate(aggregation, "user_info", UserShortDTO.class);

        System.out.println(MongoConstants.getCollectionName(UserInfo.class));

        return groupResults.getMappedResults();
    }
}
