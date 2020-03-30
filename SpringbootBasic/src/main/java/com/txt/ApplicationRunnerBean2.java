package com.txt;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ApplicationRunnerBean2 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("ApplicationRunnerBean 2");
	}

}
