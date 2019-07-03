package com.alan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Dept;

public interface DeptRepo extends JpaRepository<Dept, Long>{

}
