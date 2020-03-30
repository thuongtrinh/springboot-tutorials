package com.txt.schedule;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJobs {

	@Scheduled(fixedRate = 60000)
	public void scheduleFixedRateTask() throws InterruptedException {
		System.out.println("Task1 - " + new Date());
	}

	@Scheduled(cron = "*/31 * * * * *")
	public void scheduleTaskUsingCronExpression() throws InterruptedException {
		System.out.println("Task2 - " + new Date());
	}

}
