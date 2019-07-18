package com.alan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.repo.RoomTicketRepo;

@Service
public class RoomService {
	@Autowired
    private RoomTicketRepo roomTicketRepo;
	
	public List<RoomTicket> listAllTickets(){
		return roomTicketRepo.findAll();
	}
	
	public boolean checkAvailability(Room room, Date startTime, Date endTime){
		
		List<RoomTicket> tickets = roomTicketRepo.findByRoom(room);
		if (tickets.isEmpty()) {
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
}
