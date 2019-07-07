package com.alan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.TaskRepo;
import com.alan.dao.UserDao;
import com.alan.model.Emp;
import com.alan.model.Task;
import com.alan.service.JwtUserDetailsService;
import com.alan.service.TaskService;

@RestController
public class EmpRestController {
	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private DeptRepo deptRepo;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private TaskService taskService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/emp/respond-task")
	public String respondTask(@RequestBody Task task){
		Task updatedTask = taskRepo.findById(task.getTaskId()).orElse(null);
		updatedTask.setTaskStatus(task.getTaskStatus());
		taskService.respondTask(updatedTask);
		
		return "Delivered!";
	}
}
