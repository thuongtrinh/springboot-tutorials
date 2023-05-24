package com.txt.mongoredis.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Autowired
//    protected ObjectMapper objectMapper;

//    @Bean()
//    public RestResponseFactory restResponseFactory() {
//        RestResponseFactory restResponseFactory = new RestResponseFactory(objectMapper);
//        return restResponseFactory;
//    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("com.txt.mongoredis.controller")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Rest API")
                        .description("Documentations for Rest API v1.0")
                        .version("v1.0")
                        .license(new License().name("Rest API").url("https://localhost/")))
                // Components section defines Security Scheme "authorizationHeader"
                .components(new Components()
                        .addSecuritySchemes("authorizationHeader", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")))
                // AddSecurityItem section applies created scheme globally
                .addSecurityItem(new SecurityRequirement().addList("authorizationHeader"));
    }

}