package com.alan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
