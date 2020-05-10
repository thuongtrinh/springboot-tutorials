package com.txt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.txt.entity.Article;
import com.txt.entity.Student;
import com.txt.mapper.ArticleMapper;
import com.txt.mapper.StudentMapper;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public void run(String... args) throws Exception {
		//testArticleMapper();
		testStudentMapper();
	}

	private void testStudentMapper() {
		logger.info("Inserting student -> {}");

		// Insert student by bean
		logger.info("Insert student by bean -> {}");
		Student student1 = new Student("Jim", "Java Dev", 92, 1234568, "jimdev@gmail.com");
		Student student2 = new Student("Kay", "Angular Dev", 90, 1234569, "keyangular@gmail.com");
//		studentMapper.insert(student1);
//		studentMapper.insert(student2);
		logger.info("Article id 2 -> {}", studentMapper.findById(2));

		//Insert student by map
		Student student3 = new Student("Smith2", "Database expert2", 95, 1234599, "smith2@gmail.com");
		Map<String, Object> mapSt = new HashMap<String, Object>();
		mapSt.put("name", student3.getName());
		mapSt.put("branch", student3.getBranch());
		mapSt.put("percentage", student3.getPercentage());
		mapSt.put("phone", student3.getPhone());
		mapSt.put("email", student3.getEmail());
		logger.info("Insert student by map -> {}");
//		studentMapper.insertStudentByMap(mapSt);

		//Insert student by map
		Student student4 = new Student("Mark2", "Algorithm2", 87, 1234999, "mark2@gmail.com");
		logger.info("Insert student by @Param -> {}");
//		studentMapper.insertStudentByParam(student4.getName() , student4.getBranch()
//				, student4.getPercentage() , student4.getPhone() , student4.getEmail());

		logger.info("All student -> {}");
		studentMapper.findAll().forEach(student -> System.out.println(student));

		//MyBatis Dynamic SQL
		//Ex1
		logger.info("selectStudentWithIf -> {}");
		student1.setStudentId(1);
		studentMapper.selectStudentWithIf(student1).forEach(System.out::println);

		//Ex2
		logger.info("selectStudentWithChoose -> {}");
		studentMapper.selectStudentWithChoose(student2).forEach(System.out::println);

		//Ex3
		logger.info("updateStudentWithSet -> {}");
		Student stUpdate = studentMapper.findById(3);
		stUpdate.setName("nameUpdate");
		stUpdate.setPhone(123456777);
		int result = studentMapper.updateStudentWithSet(stUpdate);
		System.out.println(result + ": " + stUpdate);

		//Ex4
		logger.info("insertStudentListWithForeach -> {}");
		List<Student> listSt = new ArrayList<>();
		Student student5 = new Student("ThuongTX1", "Java Dev", 92, 198787878, "thuongtx1@gmail.com");
		Student student6 = new Student("ThuongTX2", "Angular Dev", 90, 198787879, "thuongtx2@gmail.com");
		listSt.add(student5);
		listSt.add(student6);
//		studentMapper.insertStudentListWithForeach(listSt);
		logger.info("Student just insert -> {}");
		int idStudent = studentMapper.findMaxId();
		studentMapper.findAll().forEach(student -> {
			if(student.getStudentId() == idStudent || student.getStudentId() == idStudent - 1){
				System.out.println(student);
			}
		});

		//Ex5:
		logger.info("selectStudentByINName -> {}");
		List<String> listName = new ArrayList<>();
		listName.add("Jim");
		listName.add("ThuongTX1");
		studentMapper.selectStudentByINName(listName).forEach(System.out::println);

		//Ex6:
		logger.info("selectStudentWithBind -> {}");
		String nameBind = "h";
		studentMapper.selectStudentWithBind(nameBind).forEach(System.out::println);
	}

	private void testArticleMapper() {
		int id = articleMapper.findMaxId();
		logger.info("Inserting -> {}", articleMapper.insert(new Article(++id, "AI", "Fadatare")));
		logger.info("Inserting -> {}", articleMapper.insert(new Article(++id, "Typescript", "Cena")));
		logger.info("Inserting -> {}", articleMapper.insert(new Article(++id, "Redis", "stark")));

		logger.info("Update 35 -> {}", articleMapper.update(new Article(35, "NoSQL", "Smark")));
		logger.info("Article id 39 -> {}", articleMapper.findById(39));

		articleMapper.deleteById(38);
		logger.info("All users -> {}");
		articleMapper.findAll().forEach(article -> System.out.println(article));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
