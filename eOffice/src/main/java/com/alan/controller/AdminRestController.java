package com.alan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alan.model.*;
import com.alan.service.RoomService;
import com.alan.service.AdminService;
import com.alan.service.DeptService;
import com.alan.service.TaskService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminRestController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoomService roomService;
	
//	========================================== control employee info ============================================
	
	@GetMapping("/emps")
	public List<Emp> findAllEmps(){
		return adminService.listAllEmps();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-emp")
	public String newEmp(@RequestBody Emp emp) {
		adminService.addEmp(emp);
		return "saved";
	}
	
	@PutMapping("/update-emp")
	public String updateEmp(@RequestBody Emp emp) {
		return adminService.updateEmp(emp);
	}
	
	@DeleteMapping("/update-emp/{id}")
	public String deleteEmpById(@PathVariable long id) {
		return adminService.removeEmp(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-emp-leave")
	public String respondEmpLeave(@RequestBody LeaveMeetingForm form){
		adminService.respondEmpLeave(form);
		return "Respond Sent!";
	}
	
//	========================================== control department info ============================================
	
	
	@GetMapping("/depts")
	public List<Dept> findAllDepts(){
		return deptService.listAllDepts();
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-dept")
	Dept newDept(@RequestBody Dept dept) {
		return deptService.addDept(dept);
	}
	

	@PutMapping("/update-dept")
	public String updateDept(@RequestBody Dept dept) {
		deptService.updateDept(dept);
		
		return "The information of dept is updated.";
	}
	
	//======================================== control task info ================================================
	

	@GetMapping("/tasks")
	public List<Task> findAllTasks(){
		return taskService.findAllTasks();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/assign-task")
	public String assignTask(@RequestBody Task task){
		return taskService.assignTask(task);
	}
	
	
	
	//======================================== control room ticket info ================================================
	
	@GetMapping("/tickets")
	public List<RoomTicket> findAllTickets(){
		return roomService.listAllTickets();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-room-reservation")
	public String replyTicket(@RequestBody RoomTicket ticket){
		adminService.updateRoomTicket(ticket);
		
		return "Status updated!";
	}
	
	
	
	//========================================================================================
	
	
	
	
//	@GetMapping("/check-emp/{empId}")
//	public String findEmpById(@PathVariable Long empId){
//		Emp emp= empRepo.findById(empId).orElse(null);
//		if (emp == null)
//			return "There is no employee with ID " + empId;
//		else
//			return "Employee " + empId + " is " + emp.getF_Name() + " " + emp.getL_Name();
//	}
//	
//	@GetMapping("/check-dept/{deptId}")
//	public String findDeptById(@PathVariable int deptId){
//		Dept dept= deptRepo.findById((long) deptId).orElse(null);
//		if (dept == null)
//			return "There is no department with ID " + deptId;
//		else
//			return "Department " + deptId + " is " + dept.getDeptName();
//	}
	
	
	
	
}
