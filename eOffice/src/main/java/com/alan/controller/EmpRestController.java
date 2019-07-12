package com.alan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.LeaveMeetingRepo;
import com.alan.dao.RoomRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.TaskRepo;
import com.alan.dao.UserDao;
import com.alan.model.Emp;
import com.alan.model.LeaveMeetingForm;
import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.model.Task;
import com.alan.service.MailService;
import com.alan.service.MeetingService;
import com.alan.service.TaskService;

@RestController
public class EmpRestController {
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
	private UserDao userDao;
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private MeetingService meetingService;
	
	
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
			boolean availability = meetingService.checkAvailability(room, ticket.getStartTime(), ticket.getEndTime());

			if (!availability) {
				return "The room is unavailable. Please try the other.";
			} else {
				Emp emp= empRepo.findById(ticket.getEmp().getEmpId()).orElse(null);
				Emp admin = empRepo.findById(ticket.getAdmin().getEmpId()).orElse(null);
				
				RoomTicket newTicket = new RoomTicket(ticket.getStartTime(), ticket.getEndTime(), ticket.getDescription());
				newTicket.setRoom(room);
				newTicket.setAdmin(admin);
				newTicket.setEmp(emp);
				roomTicketRepo.save(newTicket);
				meetingService.bookRoom(newTicket);
				return "Request Delivered!";
			}
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/emp/leave-meeting")
	public String leaveMeeting(@RequestBody LeaveMeetingForm form){
		Emp emp= empRepo.findById(form.getEmp().getEmpId()).orElse(null);
		Emp admin = empRepo.findById(form.getAdmin().getEmpId()).orElse(null);
		LeaveMeetingForm newForm = new LeaveMeetingForm(form.getLeaveType(), form.getStartDate(), form.getEndDate(), form.getReason(), emp, admin);
		leaveMeetingRepo.save(newForm);
		
		meetingService.message(newForm, newForm.getReason(), admin);
		return "Request for leaving has been sent";
	}
}
