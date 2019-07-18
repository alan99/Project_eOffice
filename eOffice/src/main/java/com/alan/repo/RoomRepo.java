package com.alan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
