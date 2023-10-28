package com.txt.mongoredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class SpringBootMongoCachingRedisApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringBootMongoCachingRedisApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootMongoCachingRedisApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info(
                """
                        \n----------------------------------------------------------
                        \tApplication '{}' is running!
                        \tProfile(s): \t{}
                        \tPort(s): \t{}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"), env.getActiveProfiles(), env.getProperty("server.port"));
    }
}
