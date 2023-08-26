package com.txt.elasticsearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI
            (
                    @Value("${server.servlet.context-path}") String contextPath,
                    @Value("${application.description}") String appDesciption,
                    @Value("${application.version}") String appVersion
            ) {

        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("Elasticsearch Rest")
                        .version(appVersion));
        return openAPI;
    }

}
