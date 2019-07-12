package com.alan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.dao.RoomTicketRepo;
import com.alan.dao.UserDao;
import com.alan.model.RoomTicket;

@Service
public class AdminService {
	@Autowired
	private RoomTicketRepo roomTicketRepo;
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MailService mailService;
	
	
	
	public void adminUpdate(RoomTicket ticket, String updatedStatus) {
		ticket.setStatus(updatedStatus);
		roomTicketRepo.save(ticket);
		
		String receiver = userDao.findByEmp(ticket.getEmp()).getUsername();
		String subject = "Status update";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
}
