package com.txt.mongoredis.repositories.mongodb.dbsecond;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.txt.mongoredis.entities.mongodb.dbsecond.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    @Query("{ 'name': ?0}")
    List<Player> findByName(String name);

    List<Player> findByNameLike(String name);

}
