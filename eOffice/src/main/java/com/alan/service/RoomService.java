package com.alan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alan.dao.EmpRepo;
import com.alan.dao.RoomRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.UserDao;
import com.alan.model.Emp;
import com.alan.model.Room;
import com.alan.model.RoomTicket;

@Service
@Repository
public class RoomService {

//	private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RoomTicketRepo roomTicketRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserDao userDao;
    
//    private Logger logger;
    
    
	public boolean checkAvailability(Room room, Date startTime, Date endTime){
		
		List<RoomTicket> tickets = roomTicketRepo.findByRoom(room);
		if (tickets.isEmpty()) {
			System.out.println("no tickets");
			return true;
		} else {
			for (RoomTicket ticket:tickets) {
				if (ticket.getStatus().equals("Approved") 
						&& ( (startTime.compareTo(ticket.getStartTime())>=0 && startTime.compareTo(ticket.getEndTime())<=0)
						||   (ticket.getStartTime().compareTo(startTime)>=0 && ticket.getStartTime().compareTo(endTime)<=0)
						))
					return false;
			}
		}
		
		return true;
	}
	
	public void bookRoom(RoomTicket ticket) {
		String receiver = userDao.findByEmp(ticket.getAdmin()).getUsername();
		String subject = "Room Reservation [" + ticket.getRoom().getRoomName() + "]";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
	
	public void adminUpdate(RoomTicket ticket, String updatedStatus) {
		ticket.setStatus(updatedStatus);
		roomTicketRepo.save(ticket);
		
		String receiver = userDao.findByEmp(ticket.getEmp()).getUsername();
		String subject = "Status update";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
}
