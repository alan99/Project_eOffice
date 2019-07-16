package com.alan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.dao.EmpRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.UserDao;
import com.alan.model.Emp;
import com.alan.model.LeaveMeetingForm;
import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.model.UserDTO;

@Service
public class EmpService {
    
    @Autowired
    private RoomTicketRepo roomTicketRepo;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EmpRepo empRepo;
    
    @Autowired
    private MailService mailService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    
    public void register(UserDTO user) {
    	Emp emp = empRepo.findByUsername(user.getUsername());
    	userDetailsService.save(user, emp);
    }
    
	public String bookRoom(RoomTicket ticket, Room room) {
		boolean availability = roomService.checkAvailability(room, ticket.getStartTime(), ticket.getEndTime());

		if (!availability) {
			return "The room is unavailable. Please try the other.";
		} else {
			Emp emp = empRepo.findById(ticket.getEmp().getEmpId()).orElse(null);
			Emp admin = empRepo.findById(ticket.getAdmin().getEmpId()).orElse(null);
			
			RoomTicket newTicket = new RoomTicket(ticket.getStartTime(), ticket.getEndTime(), ticket.getDescription());
			newTicket.setRoom(room);
			newTicket.setAdmin(admin);
			newTicket.setEmp(emp);
			roomTicketRepo.save(newTicket);
			
			String receiver = ticket.getAdmin().getUsername();
			String subject = "Room Reservation [" + ticket.getRoom().getRoomName() + "]";
			String content = "Please check";
			
			mailService.autoSendingEmail(receiver, subject, content);
			
			return "Request Delivered!";
		}
	}
	
	
	
	public void message(LeaveMeetingForm form, String msg, Emp receiver) {
//		String receiver = userDao.findByEmp(receive).getUsername();
		String subject = form.getLeaveType() + " " + form.getEmp().getF_Name() + " " + form.getEmp().getL_Name();
		
		mailService.autoSendingEmail(receiver.getUsername(), subject, msg);
	}
}
