package com.alan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.LeaveMeetingRepo;
import com.alan.dao.RoomRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.TaskRepo;
import com.alan.model.Dept;
import com.alan.model.Emp;
import com.alan.model.LeaveMeetingForm;
import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.model.Task;
import com.alan.model.UserDTO;
import com.alan.service.EmpService;
import com.alan.service.TaskService;

@RestController
@RequestMapping("/emp")
public class EmpRestController {
//	Repo
	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private RoomRepo roomRepo;
	@Autowired
	private LeaveMeetingRepo leaveMeetingRepo;
	@Autowired
	private RoomTicketRepo roomTicketRepo;
	@Autowired
	private DeptRepo deptRepo;
	
	
//	Service
	@Autowired
	private TaskService taskService;
	@Autowired
	private EmpService empService;
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register")
	public void newEmp(@RequestBody UserDTO user) {
		empService.register(user);
	}
	
	@GetMapping("/tasks")
	public List<Task> findAllEmpTasks(@RequestBody Emp emp){
		return taskRepo.findByEmp(empRepo.findById(emp.getEmpId()).orElse(null));
	}
	
	@GetMapping("/tickets")
	public List<RoomTicket> findAllEmpTickets(@RequestBody Emp emp){
		return roomTicketRepo.findByEmp(empRepo.findById(emp.getEmpId()).orElse(null));
	}
	
	@GetMapping("/depts")
	public List<Dept> findAllDepts(){
		return deptRepo.findAll();
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/respond-task")
	public String respondTask(@RequestBody Task task){
		Task updatedTask = taskRepo.findById(task.getTaskId()).orElse(null);
		updatedTask.setTaskStatus(task.getTaskStatus());
		taskService.respondTask(updatedTask);
		
		return "Delivered!";
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/booking-room")
	public ResponseEntity<String> bookMeetingRoom(@RequestBody RoomTicket ticket){
		Room room = roomRepo.findById(ticket.getRoom().getRoomId()).orElse(null);
		if (room == null) {
			return ResponseEntity.ok("There is no this room. Please type again.");
		} else {
			return ResponseEntity.ok(empService.bookRoom(ticket, room));
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/leave-meeting")
	public String leaveMeeting(@RequestBody LeaveMeetingForm form){
		Emp emp= empRepo.findById(form.getEmp().getEmpId()).orElse(null);
		Emp admin = empRepo.findById(form.getAdmin().getEmpId()).orElse(null);
		LeaveMeetingForm newForm = new LeaveMeetingForm(form.getLeaveType(), form.getStartDate(), form.getEndDate(), form.getReason(), emp, admin);
		leaveMeetingRepo.save(newForm);
		
		empService.message(newForm, newForm.getReason(), admin);
		return "Request for leaving has been sent";
	}
}
