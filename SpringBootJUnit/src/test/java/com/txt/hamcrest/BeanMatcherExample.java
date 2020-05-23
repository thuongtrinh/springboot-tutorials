package com.txt.hamcrest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import org.hamcrest.beans.HasProperty;
import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
class Student {
	private int id;
	private String name;
}

public class BeanMatcherExample {

	@Test
	public void givenBean_whenHasValue_thenCorrect() {
		Student student = new Student(29, "txt");
		assertThat(student, hasProperty("txt"));
		assertThat(student, HasProperty.hasProperty("id"));
	}

	@Test
	public void givenBean_whenHasCorrectValue_thenCorrect() {
		Student student = new Student(29, "txt");
		assertThat(student, hasProperty("name", equalTo("txt")));
	}

	@Test
	public void given2Beans_whenHavingSameValues_thenCorrect() {
		Student student1 = new Student(29, "txt");
		Student student2 = new Student(29, "txt");
		assertThat(student1, samePropertyValuesAs(student2));
	}
}
