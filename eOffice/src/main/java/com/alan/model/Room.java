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

	public Room() {}

	public Room(String roomName) {
		super();
		this.roomName = roomName;
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
	
	
}
