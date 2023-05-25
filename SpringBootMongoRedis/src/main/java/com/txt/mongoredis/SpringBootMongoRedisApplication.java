package com.txt.mongoredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringBootMongoRedisApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringBootMongoRedisApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootMongoRedisApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running!\n\t"
                        + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"), env.getActiveProfiles());
    }
}
