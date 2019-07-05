package com.alan.model;

import java.util.*;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eoffice_dept")
public class Dept {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long deptId;
	
	@Column
	private String deptName;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="empId", nullable = true)
//	private Set<Emp> emps=new HashSet<>();
	
	
	public Dept() {}
	
//	public Dept(String deptName, Set<Emp> emps) {
//		super();
//		this.deptName = deptName;
//		this.emps = emps;
//	}

	
	public Dept(String deptName) {
		super();
		this.deptName = deptName;
	}
	

//	public Dept(int deptId, String deptName) {
//		super();
//		this.deptId = deptId;
//		this.deptName = deptName;
//	}

//	public Set<Emp> getEmps() {
//		return emps;
//	}
//
//	public void setEmps(Set<Emp> emps) {
//		this.emps = emps;
//	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
