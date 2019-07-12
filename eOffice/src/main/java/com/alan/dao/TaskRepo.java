package com.alan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Emp;
import com.alan.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
	List<Task> findByEmp(Emp emp);
}
