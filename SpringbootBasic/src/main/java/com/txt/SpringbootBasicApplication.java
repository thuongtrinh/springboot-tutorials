package com.txt;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@ServletComponentScan
@SpringBootApplication
@EnableScheduling
public class SpringbootBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBasicApplication.class, args);
		
		// ApplicationContextAware is an interface that allows access to Spring Bean, resource files.
		System.out.println("------ApplicationContextAware------");
		MainAwareApp awareApp = new MainAwareApp();
		awareApp.findAllUsers().stream().forEach(
				user -> System.out.println(user.getId() + " - " + user.getUsername() 
				+ " - " + Arrays.stream(user.getRoles()).collect(Collectors.joining(","))));
	}

	@Bean
	public TaskScheduler taskScheduler() {
		final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10);
		return scheduler;
	}
}
