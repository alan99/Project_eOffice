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
			Dept d2 = new Dept("Sales");
			deptRepo.save(d1);
			deptRepo.save(d2);
			Emp emp = new Emp("Chih-Lun", "Chang", 12345678, d1);
			Emp emp2 = new Emp("Eric", "XXX", 19999999, d2);
			empRepo.save(emp);
			empRepo.save(emp2);
//			UserDTO user = new UserDTO("ofcadmncog@gmail.com", "Cogent@123", "ADMIN");
			UserDTO user = new UserDTO("alan2ndtemp@gmail.com", "kkkk", "ADMIN");
			userDetailsService.save(user, emp);
			UserDTO user2 = new UserDTO("alan.2nd.temp@gmail.com", "1234", "EMP");
			userDetailsService.save(user2, emp2);
//			mailService.autoSendingEmail(emp, user);
		};
	}
}
