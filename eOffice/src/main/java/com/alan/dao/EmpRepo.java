package com.alan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.*;

public interface EmpRepo extends JpaRepository<Emp, Long>{
//	Emp findByf_Name(String f_Name);
}
