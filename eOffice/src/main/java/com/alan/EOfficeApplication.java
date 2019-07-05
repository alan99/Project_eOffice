package com.alan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alan.dao.*;
import com.alan.model.*;
import com.alan.service.JwtUserDetailsService;
import com.alan.service.MailService;

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
			Emp emp = new Emp("Chih-Lun", "Chang", 12345678, d1);
			empRepo.save(emp);
			UserDTO user = new UserDTO("ofcadmncog@gmail.com", "Cogent@123");
			userDetailsService.save(user, emp);
//			mailService.autoSendingEmail(emp, user);
		};
	}
}
