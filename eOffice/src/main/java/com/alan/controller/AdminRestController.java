package com.alan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.LeaveMeetingRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.TaskRepo;
import com.alan.dao.UserDao;
import com.alan.model.*;
import com.alan.service.JwtUserDetailsService;
import com.alan.service.AdminService;
import com.alan.service.EmpService;
import com.alan.service.TaskService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private DeptRepo deptRepo;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private LeaveMeetingRepo leaveMeetingRepo;
	@Autowired
	private RoomTicketRepo roomTicketRepo;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private EmpService empService;
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/emps")
	public List<Emp> findAllEmps(){
		return empRepo.findAll();
	}
	
	@GetMapping("/depts")
	public List<Dept> findAllDepts(){
		return deptRepo.findAll();
	}
	
	@GetMapping("/tasks")
	public List<Task> findAllTasks(){
		return taskRepo.findAll();
	}
	
	@GetMapping("/tickets")
	public List<RoomTicket> findAllTickets(){
		return roomTicketRepo.findAll();
	}
	
	//========================================================================================
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register-emp")
	public Emp newEmp(@RequestBody Emp emp /*, @PathVariable  String deptName*/) {
		Dept dept = deptRepo.findByDeptName(emp.getDept().getDeptName());
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
	@PostMapping("/assign-task")
	public String assignTask(@RequestBody Task task){
		Emp leader = empRepo.findById(task.getLeader().getEmpId()).orElse(null);
		Emp emp= empRepo.findById(task.getEmp().getEmpId()).orElse(null);
		Task newTask = new Task(task.getTaskName(), task.getTaskText(), task.getStartDate(), task.getEndDate());
		taskService.assignTask(newTask, leader, emp);
		
		return "Delivered!";
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-room-reservation")
	public String respondRoomReservation(@RequestBody RoomTicket ticket){
		RoomTicket updatedTicket = roomTicketRepo.findById(ticket.getTicketId()).orElse(null);
		adminService.adminUpdate(updatedTicket, ticket.getStatus());
		
		return "Status updated!";
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-emp-leave")
	public String respondEmpLeave(@RequestBody LeaveMeetingForm form){
		LeaveMeetingForm updatedForm = leaveMeetingRepo.findById(form.getFormId()).orElse(null);
		updatedForm.setRespond(form.getRespond());
		leaveMeetingRepo.save(updatedForm);
		String msg = "Your request for leaving is " + updatedForm.getRespond();
		empService.message(updatedForm, msg, updatedForm.getEmp());
		return "Respond Sent!";
	}
	//========================================================================================
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-dept")
	Dept newDept(@RequestBody Dept dept) {
		return deptRepo.save(dept);
	}
	
	@GetMapping("/check-emp/{empId}")
	public String findEmpById(@PathVariable Long empId){
		Emp emp= empRepo.findById(empId).orElse(null);
		if (emp == null)
			return "There is no employee with ID " + empId;
		else
			return "Employee " + empId + " is " + emp.getF_Name() + " " + emp.getL_Name();
	}
	
	@GetMapping("/check-dept/{deptId}")
	public String findDeptById(@PathVariable int deptId){
		Dept dept= deptRepo.findById((long) deptId).orElse(null);
		if (dept == null)
			return "There is no department with ID " + deptId;
		else
			return "Department " + deptId + " is " + dept.getDeptName();
	}
	
	@PutMapping("/update-emp/{empId}")
	public String updateEmp(@RequestBody Emp emp, @PathVariable Long empId) {
		Emp newEmp = new Emp(emp.getF_Name(), emp.getL_Name(), emp.getContactNo(), emp.getDept());
		newEmp.setEmpId(empId);
		empRepo.save(newEmp);
		return "The information of employee " + empId + " is updated.";
	}
	
	@PutMapping("/update-dept/{deptId}")
	public String updateDept(@RequestBody Dept dept, @PathVariable Long deptId) {
		Dept newDept = new Dept(dept.getDeptName());
		newDept.setDeptId(deptId);
		deptRepo.save(newDept);
		return "The information of employee " + deptId + " is updated.";
	}
	
	@DeleteMapping("/delete-emp/{empId}")
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
