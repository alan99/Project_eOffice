package com.alan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Dept;

public interface DeptRepo extends JpaRepository<Dept, Long>{
	
	Dept findByDeptName(String deptName);

}
