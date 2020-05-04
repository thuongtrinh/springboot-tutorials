package com.txt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;

import com.txt.dao.EmployeeDAO;
import com.txt.dao.FamilyDAO;
import com.txt.dao.FriendDAO;
import com.txt.dao.UserDAO;
import com.txt.entity.Person;

@SpringBootApplication
@EnableCaching
public class SpringBootRedisApplication implements CommandLineRunner {

	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private FamilyDAO familyDAO;
	@Autowired
	private EmployeeDAO empDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		printTestRedis();
	}

	private void printTestRedis() {
		// List
		System.out.println("--Example of ListOperations--");
		Person p1 = new Person(1, "ThuongTX", 27);
		friendDAO.addFriend(p1);
		Person p2 = new Person(2, "TungTX", 40);
		friendDAO.addFriend(p2);

		System.out.println("Number of friends: " + friendDAO.getNumberOfFriends());
		System.out.println(friendDAO.getFriendAtIndex(1));

		friendDAO.removeFriend(p1);
		long size = friendDAO.getNumberOfFriends();
		System.out.println("Number of friends: " + size);
		for (int i = 0; i < size; i++) {
			System.out.println(friendDAO.getFriendAtIndex(i));
		}

		// Set
		System.out.println("\n--Example of SetOperations--");
		Person p11 = new Person(101, "Mother", 30);
		Person p12 = new Person(102, "Father", 25);
		Person p13 = new Person(103, "Brother", 35);

		familyDAO.addFamilyMembers(p11, p12, p13);
		System.out.println("Number of Family members: " + familyDAO.getNumberOfFamilyMembers());
		System.out.println(familyDAO.getFamilyMembers());
		System.out.println("No. of Removed Family Members: " + familyDAO.removeFamilyMembers(p11, p12));
		System.out.println(familyDAO.getFamilyMembers());

		// Hash
		System.out.println("\n--Example of HashOperations--");
		Person emp1 = new Person(11, "Alace", 45);
		Person emp2 = new Person(12, "Smith", 30);
		Person emp3 = new Person(13, "Rob", 25);
		empDAO.addEmployee(emp1);
		empDAO.addEmployee(emp2);
		empDAO.addEmployee(emp3);
		System.out.println("No. of Employees: " + empDAO.getNumberOfEmployees());
		System.out.println(empDAO.getAllEmployees());
		emp2.setAge(20);
		empDAO.updateEmployee(emp2);
		System.out.println(empDAO.getEmployee(12));

		System.out.println("--Example of StringRedisTemplate--");
		userDAO.addUserName("user001");
		System.out.println(userDAO.getUserName());
		userDAO.updateUserName("user002");
		System.out.println(userDAO.getUserName());
		userDAO.deleteUser();
		System.out.println(userDAO.getUserName());

		// RedisTemplate
		listRedisTemplate();
	}

	private void listRedisTemplate() {
		System.out.println("\n--RedisTemplate--");
		List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("redis");

		redisTemplate.opsForList().rightPushAll("redis_list", list);
		// template.opsForList().rightPushAll("redis_list", "hello", "world");
		System.out.println("Size of key redis: " + redisTemplate.opsForList().size("redis_list"));
	}
}
