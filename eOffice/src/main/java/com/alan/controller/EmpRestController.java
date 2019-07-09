package com.alan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.RoomRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.TaskRepo;
import com.alan.dao.UserDao;
import com.alan.model.Emp;
import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.model.Task;
import com.alan.service.RoomService;
import com.alan.service.TaskService;

@RestController
public class EmpRestController {
	@Autowired
	private EmpRepo empRepo;
//	@Autowired
//	private DeptRepo deptRepo;
//	@Autowired
//	private UserDao userDao;
	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private RoomRepo roomRepo;
	@Autowired
	private RoomTicketRepo roomTicketRepo;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RoomService roomService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/emp/respond-task")
	public String respondTask(@RequestBody Task task){
		Task updatedTask = taskRepo.findById(task.getTaskId()).orElse(null);
		updatedTask.setTaskStatus(task.getTaskStatus());
		taskService.respondTask(updatedTask);
		
		return "Delivered!";
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/emp/booking-room")
	public String bookMeetingRoom(@RequestBody RoomTicket ticket){
		Room room = roomRepo.findById(ticket.getRoom().getRoomId()).orElse(null);
		if (room == null) {
			return "There is no this room. Please type again.";
		} else {
			boolean availability = roomService.checkAvailability(room, ticket.getStartTime(), ticket.getEndTime());
//			boolean availability = true;
			if (!availability) {
				return "The room is unavailable. Please try the other.";
			} else {
				Emp emp= empRepo.findById(ticket.getEmp().getEmpId()).orElse(null);
				Emp admin = empRepo.findById(ticket.getAdmin().getEmpId()).orElse(null);
				
//				RoomTickets newTicket = new RoomTickets(room, ticket.getStartTime(), ticket.getEndTime(), emp, admin, ticket.getComment());
				RoomTicket newTicket = new RoomTicket(ticket.getStartTime(), ticket.getEndTime(), ticket.getDescription());
				newTicket.setRoom(room);
				newTicket.setAdmin(admin);
				newTicket.setEmp(emp);
				roomTicketRepo.save(newTicket);
				roomService.bookRoom(newTicket);
				return "Request Delivered!";
			}
		}
//		Task updatedTask = taskRepo.findById(task.getTaskId()).orElse(null);
//		updatedTask.setTaskStatus(task.getTaskStatus());
//		taskService.respondTask(updatedTask);
		
		
	}
}
