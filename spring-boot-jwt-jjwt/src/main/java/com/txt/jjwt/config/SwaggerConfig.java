package com.txt.jjwt.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("com.txt.jjwt.controller")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Rest Json Web Token with JJWT API")
                        .description("Documentations for Rest json web token with JJWT API v1.0")
                        .version("v1.0")
                        .license(new License().name("RestAPI").url("http://localhost:8080/")))

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