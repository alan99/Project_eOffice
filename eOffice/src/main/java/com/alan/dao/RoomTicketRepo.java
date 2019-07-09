package com.alan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.RoomTicket;

public interface RoomTicketRepo extends JpaRepository<RoomTicket, Long> {

}
