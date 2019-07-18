package com.alan.model;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "eoffice_room_tickets")
public class RoomTicket {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long ticketId;
	
	@ManyToOne
	@JoinColumn(name = "roomId", nullable = false)
	private Room room;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Date startTime;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Date endTime;
	
	@ManyToOne
	@JoinColumn(name = "empId", nullable = false)
	private Emp emp;
	
	@ManyToOne
	@JoinColumn(name = "adminId", nullable = false)
	private Emp admin;
	
	@Column
	private String description;
	
	@Column
	private String status;

	
	
	public RoomTicket() {}	
	
	public RoomTicket(Room room, Date startTime, Date endTime, Emp emp, Emp admin, String description) {
		super();
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.emp = emp;
		this.admin = admin;
		this.description = description;
		this.status = "Requesting";
	}

	
	
	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Emp getAdmin() {
		return admin;
	}

	public void setAdmin(Emp admin) {
		this.admin = admin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
