package com.alan.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eoffice_dept")
public class Dept {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int deptId;
	
	@Column
	private String deptName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="empId", referencedColumnName="empId")
	private Collection<Emp> emps=new ArrayList<>();
	
	
	
	public Dept(String deptName, Collection<Emp> emps) {
		super();
		this.deptName = deptName;
		this.emps = emps;
	}

	public Dept(String deptName) {
		super();
		this.deptName = deptName;
	}
	public Dept() {}

	public Collection<Emp> getEmps() {
		return emps;
	}

	public void setEmps(Collection<Emp> emps) {
		this.emps = emps;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
