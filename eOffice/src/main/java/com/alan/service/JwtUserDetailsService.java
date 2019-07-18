package com.alan.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alan.model.*;
import com.alan.repo.EmpRepo;
import com.alan.repo.UserRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private MailService mailService;
	@Autowired
	private EmpRepo empRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public User register(UserDTO user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole("EMP");
		
		Emp emp = empRepo.findByUsername(user.getUsername());
		
		String receiver = user.getUsername();
		String subject = "Welcome on-board, Mr " + emp.getF_Name() + " " + emp.getL_Name();
		String content = 
				"Welcome to on-Board into E-office Corp.\n" + 
				"Welcome Mr " + emp.getF_Name()+ ", your details as below:-\n" + 
				"empId:" + emp.getEmpId() + " \n" + 
				"Loginid:" + user.getUsername() +" \n" + 
				"Password:" + user.getPassword() + " from Users\n" ;
		
		mailService.autoSendingEmail(receiver, subject, content);
		
		return userRepo.save(newUser);
	}
	

	
	public User edit(UserDTO user) {
		User updatedUser = userRepo.findByUsername(user.getUsername());
		
		if (user.getPassword()!= null)
			updatedUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		return userRepo.save(updatedUser);
	}
	
	public String checkRole(String username) {
		return userRepo.findByUsername(username).getRole();
	}
	
	public User findUser(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void authorizeAdmin(String username) {
		User user = findUser(username);
		user.setRole("ADMIN");
		userRepo.save(user);
	}
}