package com.alan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alan.dao.CourseRepo;
import com.alan.model.Course;

@SpringBootApplication
public class CourseJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseJdbcApplication.class, args);
	}

	@Bean
	CommandLineRunner unutualuzeDB(CourseRepo prdRepo) {
		return args->{
			prdRepo.save(new Course("CS"));
			prdRepo.save(new Course("ME"));
			prdRepo.save(new Course("Math"));
		};
	}
}
