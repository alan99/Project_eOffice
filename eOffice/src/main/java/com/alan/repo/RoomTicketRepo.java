package com.alan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Emp;
import com.alan.model.Room;
import com.alan.model.RoomTicket;

public interface RoomTicketRepo extends JpaRepository<RoomTicket, Long> {
	List<RoomTicket> findByRoom(Room room);
	List<RoomTicket> findByEmp(Emp emp);
}
