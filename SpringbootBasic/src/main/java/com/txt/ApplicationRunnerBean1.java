package com.txt;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
* @author ThuongTX
* @comment ApplicationRunner accepts spring ApplicationArguments as an argument and run() method 
* The run() method of ApplicationRunner is executed just before SpringApplication finishes its startup
* We can create more than one bean of ApplicationRunner -> use spring @Order annotation or Ordered interface.
*/
@Component
@Order(1)
public class ApplicationRunnerBean1 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("ApplicationRunnerBean 1");
	}
}
