package com.txt;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.txt.properties.Project;
import com.txt.properties.Student;
import com.txt.properties.Team;
import com.txt.service.BeanUtilService;

/**
 * @author ThuongTX
 * @comment CommandLineRunner accepts array of String as an argument and run() method 
 * The run() method of CommandLineRunner is executed just before SpringApplication finishes its startup
 * We can create more than one bean of CommandLineRunner -> use spring @Order annotation or Ordered interface.
 */
@Component
@Order(3)
public class CommandLineRunnerBean implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerBean.class);

	@Autowired
	Environment env;

	@Override
	public void run(String... args) throws Exception {
		String strArgs = Arrays.stream(args).collect(Collectors.joining("|"));
		logger.info("Application started with arguments:" + strArgs);

		// Read info properties
		ReadPropertiesInfo();
	}

	private void ReadPropertiesInfo() {

		System.out.println("\n============Start read properties============\n");
		//final ApplicationContext ctx = SpringApplication.run(SpringbootBasicApplication.class, null);
		final Team team = BeanUtilService.getBean(Team.class);

		System.out.println("--- Team ---");
		System.out.println(team);
		
		System.out.println("--- Team Employee---");
		team.getEmployees().forEach(e -> System.out.println(e));
		
		System.out.println("--- Technologies ---");
		System.out.println(team.getTechnologies().size());
		team.getTechnologies().forEach(((t, v) -> System.out.println(t + " - " + v)));
		
		System.out.println("--- Company ---");
		System.out.println(team.getCompany());
		
		System.out.println("--- Clients ---");
		for (String c : team.getClients()) {
			System.out.println(c);
		}

		System.out.println("--- Project ---");
		final Project project = BeanUtilService.getBean(Project.class);
		System.out.println(project);

		//Use @Value
		System.out.println("--- Student ---");
		final Student student = BeanUtilService.getBean(Student.class);
		System.out.println(student.getClazz() + "-" + student.getName());

		//Use Environment
		System.out.println(env.getProperty("message"));
		System.out.println("============Finish read properties============\n");
	}

}
