package com.txt.mongoredis.repositories.mongodb.dbfirst;

import com.txt.mongoredis.entities.mongodb.dbfirst.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
    List<ActivityLog> findAllByIdAndStatusOrderByCreatedDateDesc(String id, String status);
}
