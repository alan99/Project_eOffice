package com.alan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alan.model.Emp;
import com.alan.model.LeaveMeetingForm;
import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.model.Task;
import com.alan.model.UserDTO;
import com.alan.repo.EmpRepo;
import com.alan.repo.LeaveMeetingRepo;
import com.alan.repo.RoomRepo;
import com.alan.repo.RoomTicketRepo;
import com.alan.repo.TaskRepo;

@Service
public class EmpService {
    
    @Autowired
    private RoomTicketRepo roomTicketRepo;
    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private LeaveMeetingRepo leaveMeetingRepo;
    
    
    @Autowired
    private MailService mailService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    
    public List<Emp> listAllEmps(){
		return empRepo.findAll();
	}
    
    
    
    
    public void register(UserDTO user) {
    	if (userDetailsService.findUser(user.getUsername()) == null 
    	 && empRepo.findByUsername(user.getUsername()) != null)
    		userDetailsService.register(user);
    }
    
    
    
    
    public List<Task> listAllTasks(Emp emp){
    	return taskRepo.findByEmp(empRepo.findByUsername(emp.getUsername()));
    }
    
    
    
    
    public List<RoomTicket> listAllRoomTickets(Emp emp){
    	return roomTicketRepo.findByEmp(empRepo.findById(emp.getEmpId()).orElse(null));
    }
    
    
    
//	public RoomTicket bookRoom(RoomTicket ticket) {
//		
//		Room room = roomRepo.findById(ticket.getRoom().getRoomId()).orElse(null);
//		
//		if (room == null) {
//			return null;
//		} else {
//			return roomService.bookRoom(ticket);
//
////			if (!availability) {
////				return ResponseEntity.ok("The room is unavailable. Please try the other.");
////			} else {
////				ticket.setRoom(room);
////				Emp emp = empRepo.findById(ticket.getEmp().getEmpId()).orElse(null);		ticket.setEmp(emp);
////				Emp admin = empRepo.findById(ticket.getAdmin().getEmpId()).orElse(null);	ticket.setAdmin(admin);
////				
////				roomTicketRepo.save(ticket);
////				
////				String receiver = ticket.getAdmin().getUsername();
////				String subject = "Room Reservation [" + ticket.getRoom().getRoomName() + "]";
////				String content = "Please check";
////				
////				mailService.autoSendingEmail(receiver, subject, content);
////				
////				return ResponseEntity.ok("Request Delivered!");
////			}
//		}
//	}
	
	
	public void leaveMeeting(LeaveMeetingForm form) {
		Emp emp= empRepo.findById(form.getEmp().getEmpId()).orElse(null);			form.setEmp(emp);
		Emp admin = empRepo.findById(form.getAdmin().getEmpId()).orElse(null);		form.setAdmin(admin);
		
		leaveMeetingRepo.save(form);
		
		message(form, form.getReason(), admin);
	}
	
	
	public void message(LeaveMeetingForm form, String msg, Emp receiver) {
		String subject = form.getLeaveType() + " " + form.getEmp().getF_Name() + " " + form.getEmp().getL_Name();
		
		mailService.autoSendingEmail(receiver.getUsername(), subject, msg);
	}
}
