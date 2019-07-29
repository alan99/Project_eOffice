package com.alan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.model.*;
import com.alan.service.DeptService;
import com.alan.service.EmpService;
import com.alan.service.RoomService;
import com.alan.service.TaskService;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpRestController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private EmpService empService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoomService roomService;
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register")
	public void newEmp(@RequestBody UserDTO user) {
		empService.register(user);
	}

	@GetMapping("/emps")
	public List<Emp> findAllEmps(){
		return empService.listAllEmps();
	}
	
	@GetMapping("/depts")
	public List<Dept> findAllDepts(){
		return deptService.listAllDepts();
	}
	
	
	
	@PostMapping("/tasks")
	public List<Task> findAllEmpTasks(@RequestBody Emp emp){
		return empService.listAllTasks(emp);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/update-task")
	public ResponseEntity<?>  respondTask(@RequestBody Task task){
		
		taskService.respondTask(task);
		
		return ResponseEntity.ok("Delivered!");
	}
	
	
	
	@GetMapping("/rooms")
	public List<Room> findAllRooms(){
		return roomService.listAllRooms();
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
	
	@GetMapping("/my-tickets")
	public List<RoomTicket> findAllEmpTickets(@RequestBody Emp emp){
		return empService.listAllRoomTickets(emp);
	}
	
	
	
	
	
	
	
	
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/send-leave-form")
	public LeaveMeetingForm leaveMeeting(@RequestBody LeaveMeetingForm form){
		return empService.leaveMeeting(form);
	}
}
