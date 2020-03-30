package com.txt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:myteam.properties", "classpath:project-util.properties" })
public class ConfigProperties {

	@Bean
	@ConfigurationProperties(prefix = "project")
	public Project getProject() {
		return new Project();
	}
}
