package com.alan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alan.dao.*;
import com.alan.model.*;

@SpringBootApplication
public class EOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EOfficeApplication.class, args);
	}

//	@Bean
//	CommandLineRunner unutualuzeDB(EmpRepo empRepo) {
//		return args->{
//			empRepo.save(new Emp("Alan", "Chang", "alan@gmail.com", 1234, new Dept("R&D")));
//		};
//	}
}
