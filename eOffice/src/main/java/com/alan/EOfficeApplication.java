package com.alan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alan.dao.*;
import com.alan.model.*;
import com.alan.service.JwtUserDetailsService;

@SpringBootApplication
public class EOfficeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EOfficeApplication.class, args);
	}

	@Bean
	CommandLineRunner unutualuzeDB(DeptRepo deptRepo, EmpRepo empRepo, JwtUserDetailsService userDetailsService) {
		return args->{			
			Dept d1 = new Dept("RD");
			deptRepo.save(d1);
			deptRepo.save(new Dept("Sales"));
			Emp emp = new Emp("alan", "ccc", 123, d1);
			empRepo.save(emp);
			userDetailsService.save(new UserDTO("alan@ga.com", "1234"), emp);
		};
	}
}
