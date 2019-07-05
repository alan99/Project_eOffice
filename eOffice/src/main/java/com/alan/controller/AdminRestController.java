package com.alan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.UserDao;
import com.alan.model.*;
import com.alan.service.JwtUserDetailsService;

@RestController
public class AdminRestController {
	
	
// create employee
//	create dept
	@Autowired
	private EmpRepo empRepo;
	
	@Autowired
	private DeptRepo deptRepo;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@GetMapping("/emps")
	public List<Emp> findAllEmps(){
		return empRepo.findAll();
	}
	
	@GetMapping("/depts")
	public List<Dept> findAllDepts(){
		return deptRepo.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/depts/register-emp/{deptName}")
	public Emp newEmp(@RequestBody Emp emp, @PathVariable String deptName) {
		Dept dept = deptRepo.findByDeptName(deptName);
		Emp newEmp = new Emp(emp.getF_Name(), emp.getL_Name(), emp.getContactNo());
		newEmp.setDept(dept);
		empRepo.save(newEmp);
		return newEmp;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/depts/register-user/{empId}")
	public ResponseEntity<?> newUser(@RequestBody UserDTO user, @PathVariable long empId) throws Exception {
		Emp emp= empRepo.findById(empId).orElse(null);
		return ResponseEntity.ok(userDetailsService.save(user, emp));
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/depts")
	Dept newDept(@RequestBody Dept dept) {
		return deptRepo.save(dept);
	}
	
	@GetMapping("/emps/{empId}")
	public String findEmpById(@PathVariable Long empId){
		Emp emp= empRepo.findById(empId).orElse(null);
		if (emp == null)
			return "There is no employee with ID " + empId;
		else
			return "Employee " + empId + " is " + emp.getF_Name() + " " + emp.getL_Name();
	}
	
	@GetMapping("/depts/{deptId}")
	public String findDeptById(@PathVariable int deptId){
		Dept dept= deptRepo.findById((long) deptId).orElse(null);
		if (dept == null)
			return "There is no department with ID " + deptId;
		else
			return "Department " + deptId + " is " + dept.getDeptName();
	}
	
	@PutMapping("/emps/{empId}")
	public String updateEmp(@RequestBody Emp emp, @PathVariable Long empId) {
		Emp newEmp = new Emp(emp.getF_Name(), emp.getL_Name(), emp.getContactNo(), emp.getDept());
		newEmp.setEmpId(empId);
		empRepo.save(newEmp);
		return "The information of employee " + empId + " is updated.";
	}
	
	@PutMapping("/depts/{deptId}")
	public String updateDept(@RequestBody Dept dept, @PathVariable Long deptId) {
		Dept newDept = new Dept(dept.getDeptName());
		newDept.setDeptId(deptId);
		deptRepo.save(newDept);
		return "The information of employee " + deptId + " is updated.";
	}
	
	@DeleteMapping("/emps/{empId}")
	public String deleteEmpById(@PathVariable Long empId) {
		Emp emp = empRepo.findById(empId).orElse(null);
		if (emp == null)
			return "ID " + empId + " is invalid. Please try the other.";
		else {
			String empFName = emp.getF_Name(), empLName = emp.getL_Name();
			DAOUser user = userDao.findByEmp(emp);
			empRepo.deleteById(empId);
			userDao.deleteById(user.getId());
			return "Employee " + empFName + " " + empLName + " is removed from the database";
		}
	}
}
