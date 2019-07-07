package com.alan.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;
	private String password;
	private String role;
	private Emp emp;
	
	//need default constructor for JSON Parsing
	public JwtRequest() {}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	
}
