package com.alan.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alan.model.DAOUser;
import com.alan.model.Emp;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Long> {
	DAOUser findByUsername(String username);
//	DAOUser findByEmp(Emp emp);
}
