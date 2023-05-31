package com.txt.mongoredis.config.mongo;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.txt.mongoredis.repositories.mongodb.dbsecond", mongoTemplateRef = "dbsecondtemplate")
public class MongoSecondConfiguration {

    //region dbsecond Collection Config.
    @Primary
    @Bean(name = "dbsecondproperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.dbsecond")
    public MongoProperties getDBSecondProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean
    public MongoDatabaseFactory dbsecondFactory(MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }

    @Primary
    @Bean(name = "dbsecondtemplate")
    public MongoTemplate dbsecondMongoTemplate() {
        return new MongoTemplate(dbsecondFactory(getDBSecondProperties()));
    }
    //endregion
}
