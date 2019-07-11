package com.alan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eoffice_rooms")
public class Room {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long roomId;
	
	@Column
	private String roomName;
	
	@Column
	private String roomType;

	public Room() {}

	public Room(String roomName, String roomType) {
		super();
		this.roomName = roomName;
		this.roomType = roomType;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
}
