package com.txt.mongoredis.repositories.mongodb.dbfirst;

import com.txt.mongoredis.entities.mongodb.dbfirst.Books;
import com.txt.mongoredis.entities.mongodb.dbfirst.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends MongoRepository<Books, String>, QuerydslPredicateExecutor<Country> {

}
