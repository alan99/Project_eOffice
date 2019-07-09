package com.alan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alan.dao.EmpRepo;
import com.alan.dao.RoomRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.UserDao;
import com.alan.model.Emp;
import com.alan.model.Room;
import com.alan.model.RoomTicket;

@Service
public class RoomService {

//	private DataSource dataSource;
//    private JdbcTemplate jdbcTemplateObject;
    
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
    
    
    
	public boolean checkAvailability(Room room, Date startTime, Date endTime){
		String sql = "select * as count(*) from eoffice_room_tickets where room_id=? and (start_time )";
		
		return true;
	}
	
	public void bookRoom(RoomTicket ticket) {
		String receiver = userDao.findByEmp(ticket.getAdmin()).getUsername();
		String subject = "Room Reservation [" + ticket.getRoom().getRoomName() + "]";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
	
	public void adminUpdate(RoomTicket ticket, String updatedStatus) {
		ticket.setStatus(ticket.getStatus());
		roomTicketRepo.save(ticket);
		
		String receiver = userDao.findByEmp(ticket.getEmp()).getUsername();
		String subject = "Status update";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
}
