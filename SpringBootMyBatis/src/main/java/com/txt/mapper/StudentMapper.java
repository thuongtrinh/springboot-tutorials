package com.txt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.txt.entity.Student;

@Mapper
public interface StudentMapper {

	@Select("SELECT COALESCE(max(id), 0) FROM student")
	public int findMaxId();

	@Results(id="studentResults", value ={ 
		@Result(property = "studentId", column = "id"), 
		@Result(property = "name", column = "name"),
		@Result(property = "branch", column = "branch"),
		@Result(property = "percentage", column = "percentage"),
		@Result(property = "phone", column = "phone"),
		@Result(property = "email", column = "email")
	})
	@Select("SELECT * FROM student")
	public List<Student> findAll();

	@ResultMap("studentResults")
	@Select("SELECT * FROM student WHERE id = #{id}")
	public Student findById(int id);

	@Delete("DELETE FROM student WHERE id = #{id}")
	public int deleteById(int id);

	//New, parameter is a bean
	@Insert("INSERT INTO student(name, branch, percentage, phone, email) "
			+ " VALUES (#{name}, #{branch}, #{percentage}, #{phone}, #{email})")
	public int insert(Student student);

	//New, parameter is a Map
	@Insert("INSERT INTO student (name, branch, percentage, phone, email) "
			+ " VALUES (#{name}, #{branch}, #{percentage}, #{phone}, #{email})")
	int insertStudentByMap(Map<String, Object> map);

	//Added, parameter is multiple values, need to be decorated with @Param
	//MyBatis The parameter uses the @Param string, which is the same as the parameter in general @Param
	@Insert("INSERT INTO student (name, branch, percentage, phone, email) "
			+ " VALUES (#{name}, #{branch}, #{percentage}, #{phone}, #{email})")
	int insertStudentByParam(@Param("name") String name
			, @Param("branch") String branch
			, @Param("percentage") int percentage
			, @Param("phone") int phone 
			, @Param("email") String email);

	// MyBatis Dynamic SQL
	@Select("<script>" + "SELECT * FROM student									"
			+ "<where>															"
			+ "  <if test='studentId != null and studentId != &quot;&quot;'>	"
			+ "     AND id = #{studentId}										"
			+ "  </if>															"
			+ "  <if test='name != null and name != &quot;&quot;'>				"
			+ "     AND name LIKE CONCAT('%', #{name}, '%')						"
			+ "  </if>															"
			+ "</where>															"
			+ "</script>														")
	@ResultMap("studentResults")
	List<Student> selectStudentWithIf(Student student);

	/**
	* choose when otherwise Java-like Swich, select an item
	* when...when...otherwise... == if... if...else... 
	*/
	@Select("<script>"
			+ "select * from student												"
			+ "<where>																"
			+ "  <choose>															"
			+ "      <when test='studentId != null and studentId != &quot;&quot;'>	"
			+ "         and id = #{studentId}										"
			+ "      </when>														"
			+ "      <otherwise test='name != null and name != &quot;&quot;'>		"
			+ "         and name like CONCAT('%', #{name}, '%')						"
			+ "      </otherwise>													"
			+ "  </choose>															"
			+ "</where>																"
			+ "</script>															")
	@ResultMap("studentResults")
	List<Student> selectStudentWithChoose(Student student);

	/**
	 * set Update statements dynamically, like <where>
	 */
	@Update("<script>											"
			+ "update student									"
			+ "<set>											"
			+ "  <if test='name != null'> name=#{name},	   </if>"
			+ "  <if test='phone != null'> phone=#{phone}, </if>"
			+ "</set>											"
			+ "where id = #{studentId}							"
			+ "</script>										")
	int updateStudentWithSet(Student student);

	/**
	 * foreach Traverse a collection, often used in batch updates and IN in conditional statements
	 * foreach Bulk Update
	*/
	@Insert("<script>																				"
			+ "INSERT INTO student																	"
			+ " (name, branch, percentage, phone, email)											"
			+ "VALUES																				"
			+ "<foreach collection='list' item='item' index='index' separator=','>					"
			+ "   (#{item.name}, #{item.branch}, #{item.percentage}, #{item.phone}, #{item.email})	"
			+ "</foreach>																			"
			+ "</script>																			")
	int insertStudentListWithForeach(List<Student> list);

	/**
	* foreach IN in conditional statement
	*/
	@Select("<script>"
			+ "SELECT * FROM student                                    "
			+ "WHERE name IN                                             "
			+ "  <foreach collection='list' item='item' index='index'    "
			+ "       open='(' separator=',' close=')' >                 "
			+ "       #{item}                                            "
			+ "  </foreach>                                              "
			+ "</script>                                                 ")
	@ResultMap("studentResults")
	List<Student> selectStudentByINName(List<String> list);

	/**
	* bind Create a variable that is bound to the context
	*/
	@Select("<script>                                               "
			+ "<bind name=\"lname\" value=\"'%' + name + '%'\"  />  "
			+  "SELECT *                                            "
			+  "FROM student                                        "
			+  "WHERE name like #{lname}                            "
			+  "</script>                                           ")
	@ResultMap("studentResults")
	List<Student> selectStudentWithBind(@Param("name") String name);

}

