package com.txt.security.registration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class SecurityRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SecurityRegistrationApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info("""
                        \n----------------------------------------------------------
                                \tApplication '{}' is running!
                                \tPort: \t{}
                                \tProfile(s): \t{}\n----------------------------------------------------------
                                """,
                env.getProperty("spring.application.name"), env.getProperty("server.port"), env.getActiveProfiles());
    }

}
