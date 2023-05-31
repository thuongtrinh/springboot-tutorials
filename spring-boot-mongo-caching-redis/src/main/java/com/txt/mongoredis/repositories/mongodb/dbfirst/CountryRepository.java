package com.txt.mongoredis.repositories.mongodb.dbfirst;

import com.txt.mongoredis.entities.mongodb.dbfirst.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends MongoRepository<Country, String>, QuerydslPredicateExecutor<Country> {

    List<Country> findAllByOrderByName();

}
