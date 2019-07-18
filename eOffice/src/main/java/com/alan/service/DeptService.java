package com.alan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.model.Dept;
import com.alan.repo.DeptRepo;

@Service
public class DeptService {

	@Autowired
	private DeptRepo deptRepo;
	
	public List<Dept> listAllDepts(){
		return deptRepo.findAll();
	}
	
	public Dept findDept(long id) {
		return deptRepo.findById(id).orElse(null);
	}
	
	public Dept addDept(Dept dept) {
		return deptRepo.save(dept);
	}
	
	public Dept updateDept(Dept dept) {
		Dept updateDept = deptRepo.findById(dept.getDeptId()).orElse(null);
		updateDept.setDeptName(dept.getDeptName());
		return deptRepo.save(updateDept);
	}
}
