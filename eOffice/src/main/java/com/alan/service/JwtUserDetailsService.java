package com.alan.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alan.dao.UserDao;
import com.alan.model.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private MailService mailService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}
	
	public DAOUser save(UserDTO user, Emp emp) {
		DAOUser newUser = new DAOUser();
		
		String receiver = user.getUsername();
		String subject = "Welcome on-board, Mr " + emp.getF_Name() + " " + emp.getL_Name();
		String content = 
				"Welcome to on-Board into E-office Corp.\n" + 
				"Welcome Mr " + emp.getF_Name()+ ", your details as below:-\n" + 
				"empId:" + emp.getEmpId() + " \n" + 
				"Loginid:" + user.getUsername() +" \n" + 
				"Password:" + user.getPassword() + " from Users\n" ;
		
		mailService.autoSendingEmail(receiver, subject, content);
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
//		newUser.setEmp(emp);
		return userDao.save(newUser);
	}
	
	public DAOUser edit(UserDTO user) {
		DAOUser updatedUser = userDao.findByUsername(user.getUsername());
		
		if (user.getPassword()!= null)
			updatedUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
//		if (user.getEmp() != null)
//			updatedUser.setEmp(user.getEmp());
		
		return userDao.save(updatedUser);
	}
	
	public String checkRole(String username) {
		return userDao.findByUsername(username).getRole();
	}
}