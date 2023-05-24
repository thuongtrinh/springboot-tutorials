package com.txt.mongoredis.config.mongo;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.txt.mongoredis.repositories.mongodb.dbfirst", mongoTemplateRef = "dbfirsttemplate")
public class MongoFirstConfiguration {

    //region dbfirst Collection Config.
    @Bean(name = "dbfirstproperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.dbfirst")
    public MongoProperties getDBFirstProperties() {
        return new MongoProperties();
    }

    @Bean
    public MongoDatabaseFactory dbfirstFactory(MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }

    @Bean(name = "dbfirsttemplate")
    public MongoTemplate dbfirstMongoTemplate() {
        return new MongoTemplate(dbfirstFactory(getDBFirstProperties()));
    }
    //endregion
}
