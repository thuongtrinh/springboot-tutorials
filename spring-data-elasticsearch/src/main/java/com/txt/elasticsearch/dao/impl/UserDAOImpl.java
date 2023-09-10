package com.txt.elasticsearch.dao.impl;

import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.txt.elasticsearch.dao.UserDAO;
import com.txt.elasticsearch.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.user.type}")
    private String userTypeName;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public List<User> getAllUsers() {
        NativeSearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .build();

        List<User> users = new ArrayList<>();
        elasticsearchRestTemplate.search(getAllQuery, User.class).stream().forEach(s -> users.add(s.getContent()));
        return users;
    }

    @Override
    public User getUserById(String userId) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("userId", userId);
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<User> users = elasticsearchRestTemplate.search(searchQuery, User.class,
                IndexCoordinates.of(indexName));

        List<User> userMatches = new ArrayList<>();
        users.forEach(searchHit -> userMatches.add(searchHit.getContent()));

        if (!userMatches.isEmpty()) {
            return userMatches.get(0);
        }
        return null;
    }

    @Override
    public User addNewUser(User user) {
//        IndexQuery userQuery = new IndexQuery();
//        userQuery.setIndexName(indexName);
//        userQuery.setType(userTypeName);
//        userQuery.setObject(user);
//        elasticsearchRestTemplate.refresh(indexName);
//        log.info("User indexed: {}", user);

        user = elasticsearchRestTemplate.save(user);
        return user;
    }

    @Override
    public Object getAllUserSettings(String name) {
        NativeSearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", name))
                .build();

        List<User> users = new ArrayList<>();
        elasticsearchRestTemplate.search(getAllQuery, User.class).stream().forEach(s -> users.add(s.getContent()));

        if (!users.isEmpty()) {
            System.out.println("settings: " + users.get(0).getUserSettings().toString());
            return users.get(0).getUserSettings();
        }

        return users;
    }

    @Override
    public String getUserSetting(String name, String key) {
        NativeSearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", name))
                .build();

        List<User> users = new ArrayList<>();
        elasticsearchRestTemplate.search(getAllQuery, User.class).stream().forEach(s -> users.add(s.getContent()));

        if (!users.isEmpty()) {
            return users.get(0).getUserSettings().get(key);
        }

        return null;
    }

    @Override
    public String addUserSetting(String name, String key, String value) {
        NativeSearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", name))
                .build();

        List<User> users = new ArrayList<>();
        elasticsearchRestTemplate.search(getAllQuery, User.class).stream().forEach(s -> users.add(s.getContent()));

        if (!users.isEmpty()) {
            User user = users.get(0);
            user.getUserSettings().put(key, value);

            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(user.getUserId())
                    .withObject(user).build();

            String documentId = elasticsearchRestTemplate
                    .index(indexQuery, IndexCoordinates.of(indexName));

            return "Setting added." + documentId;
        }

        return null;
    }
}
