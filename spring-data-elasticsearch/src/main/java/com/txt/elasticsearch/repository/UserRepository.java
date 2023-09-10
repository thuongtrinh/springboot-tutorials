package com.txt.elasticsearch.repository;

import com.txt.elasticsearch.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    Optional<User> findByUserId(String userId);
}
