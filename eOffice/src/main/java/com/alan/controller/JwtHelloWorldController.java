package com.alan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtHelloWorldController {
	
	@RequestMapping({ "/jwt" })
	public String dbConnection() {
		return "Welcome to  JWT";
	}
}
