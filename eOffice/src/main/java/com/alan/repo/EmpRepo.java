package com.alan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Emp;

public interface EmpRepo extends JpaRepository<Emp, Long>{
	Emp findByUsername(String username);
}
