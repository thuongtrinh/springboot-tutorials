package com.txt.mongoredis.repositories.mongodb.dbsecond;

import com.txt.mongoredis.entities.mongodb.dbsecond.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
