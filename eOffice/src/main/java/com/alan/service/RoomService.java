package com.alan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.repo.EmpRepo;
import com.alan.repo.RoomRepo;
import com.alan.repo.RoomTicketRepo;

@Service
public class RoomService {
	@Autowired
	private EmpRepo empRepo;
	@Autowired
    private RoomTicketRepo roomTicketRepo;
	@Autowired
	private RoomRepo roomRepo;
	@Autowired
	private MailService mailService;
	
	public List<Room> listAllRooms(){
		return roomRepo.findAll();
	}
	
	public List<RoomTicket> listAllTickets(){
		return roomTicketRepo.findAll();
	}
	
	public Room addRoom(Room room) {
		return roomRepo.save(room);
	}
	
	public RoomTicket bookRoom(RoomTicket ticket){
		
		ticket.setStatus("Requesting");
		ticket.setAdmin(empRepo.findById(ticket.getAdmin().getEmpId()).orElse(null));
		ticket.setEmp(empRepo.findByUsername(ticket.getEmp().getUsername()));
		ticket.setRoom(roomRepo.findById(ticket.getRoom().getRoomId()).orElse(null));
		
		List<RoomTicket> reservations = roomTicketRepo.findByRoom(ticket.getRoom());
		
		if (!reservations.isEmpty()) {
			for (RoomTicket reservation:reservations) {
				if (reservation.getEmp() != ticket.getEmp() && reservation.getStatus().equals("Approved") 
					&& ( (ticket.getStartTime().compareTo(reservation.getStartTime())>=0 && ticket.getStartTime().compareTo(reservation.getEndTime())<=0)
					||   (reservation.getStartTime().compareTo(ticket.getStartTime())>=0 && reservation.getStartTime().compareTo(ticket.getEndTime())<=0)	)
					)
				{		
					return reservation;	
				}
			}
		}
		ticket.setAdmin(empRepo.findById(ticket.getAdmin().getEmpId()).orElse(null));
		ticket.setEmp(empRepo.findByUsername(ticket.getEmp().getUsername()));
		ticket.setRoom(roomRepo.findById(ticket.getRoom().getRoomId()).orElse(null));
		
		String receiver = ticket.getAdmin().getUsername();
		String subject = "Room Reservation [" + ticket.getRoom().getRoomName() + "]";
		String content = "Please check";
	
		mailService.autoSendingEmail(receiver, subject, content);
		
		return roomTicketRepo.save(ticket);
	}
	
	public RoomTicket removeTicket(long id) {
		RoomTicket ticket = roomTicketRepo.findById(id).orElse(null);
		roomTicketRepo.deleteById(id);
		return ticket;
	}
}
