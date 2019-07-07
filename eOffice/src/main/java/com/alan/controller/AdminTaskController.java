package com.alan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.dao.EmpRepo;
import com.alan.dao.TaskRepo;
import com.alan.dao.UserDao;
import com.alan.model.Dept;
import com.alan.model.Emp;
import com.alan.model.Task;

@RestController
public class AdminTaskController {
	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskRepo taskRepo;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/task/assign")
	public void assignTask(@RequestBody Task task, @PathVariable String deptName) {
//		Dept dept = deptRepo.findByDeptName(deptName);
//		Emp newEmp = new Emp(emp.getF_Name(), emp.getL_Name(), emp.getContactNo());
//		newEmp.setDept(dept);
//		empRepo.save(newEmp);
		
	}
}
