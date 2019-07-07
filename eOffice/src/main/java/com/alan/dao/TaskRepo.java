package com.alan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
