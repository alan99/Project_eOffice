package com.alan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alan.model.*;
import com.alan.service.RoomService;
import com.alan.service.AdminService;
import com.alan.service.DeptService;
import com.alan.service.EmpService;
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
	private EmpService empService;
	@Autowired
	private RoomService roomService;
	
//	========================================== control employee info ============================================
	
	@GetMapping("/emps")
	public List<Emp> findAllEmps(){
		return adminService.listAllEmps();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-emp")
	public Emp newEmp(@RequestBody Emp emp) {
		return adminService.addEmp(emp);
	}
	
	@PutMapping("/update-emp")
	public Emp updateEmp(@RequestBody Emp emp) {
		return adminService.updateEmp(emp);
	}
	
	@DeleteMapping("/update-emp/{id}")
	public Emp deleteEmpById(@PathVariable long id) {
		return adminService.removeEmp(id);
	}
	
	
//	========================================== control department info ============================================
	
	
	@GetMapping("/depts")
	public List<Dept> findAllDepts(){
		return deptService.listAllDepts();
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-dept")
	public Dept newDept(@RequestBody Dept dept) {
		return deptService.addDept(dept);
	}
	

	@PutMapping("/update-dept")
	public Dept updateDept(@RequestBody Dept dept) {
		return deptService.updateDept(dept);
	}
	
	//======================================== control task info ================================================
	

	@GetMapping("/tasks")
	public List<Task> findAllTasks(){
		return taskService.findAllTasks();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/assign-task")
	public ResponseEntity<?> assignTask(@RequestBody Task task){
		return ResponseEntity.ok(taskService.assignTask(task));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/update-task")
	public Task updateTask(@RequestBody Task task){
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/tasks/{id}")
	public Task deleteTaskById(@PathVariable long id) {
		return taskService.removeTask(id);
	}
	
	//======================================== control room ticket info ================================================
	
	@GetMapping("/rooms")
	public List<Room> findAllRooms(){
		return roomService.listAllRooms();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-room")
	public Room addRoom(@RequestBody Room room){
		return roomService.addRoom(room);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/book-room")
	public RoomTicket bookMeetingRoom(@RequestBody RoomTicket ticket){
		return roomService.bookRoom(ticket);
	}
	
	@GetMapping("/tickets")
	public List<RoomTicket> findAllTickets(){
		return roomService.listAllTickets();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-room-ticket")
	public RoomTicket respondRoomTicket(@RequestBody RoomTicket ticket){
		return adminService.updateRoomTicket(ticket);
	}
	
	@DeleteMapping("/tickets/{id}")
	public RoomTicket deleteTicketById(@PathVariable long id) {
		return roomService.removeTicket(id);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-room-reservation")
	public ResponseEntity<?> replyTicket(@RequestBody RoomTicket ticket){
		adminService.updateRoomTicket(ticket);
		
		return ResponseEntity.ok("Status updated!");
	}
	
	
	
	//========================================= Leave Management ===============================================
	
	@GetMapping("/leave-forms")
	public List<LeaveMeetingForm> findAllForms(){
		return adminService.listAllLeaving();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/send-leave-form")
	public LeaveMeetingForm leaveMeeting(@RequestBody LeaveMeetingForm form){
		return empService.leaveMeeting(form);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-leave-form")
	public LeaveMeetingForm respondEmpLeave(@RequestBody LeaveMeetingForm form){
		return adminService.respondEmpLeave(form);
	}
	
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
