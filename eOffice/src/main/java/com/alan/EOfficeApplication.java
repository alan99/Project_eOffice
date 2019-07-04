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

	@Bean
	CommandLineRunner unutualuzeDB(DeptRepo deptRepo, EmpRepo empRepo) {
		return args->{			
			Dept d1 = new Dept("RD");
			deptRepo.save(d1);
			deptRepo.save(new Dept("Sales"));
			empRepo.save(new Emp("alan", "ccc", "aaa@am.com", 123, d1));
		};
	}
}
