package com.alan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Email;


@Entity
@Table(name = "eoffice_emp")
public class Emp {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long empId;
	
	@Column
	private String f_Name;
	
	@Column
	private String l_Name;
	
	@Column
	private int contactNo;
	
	@ManyToOne
	@JoinColumn(name = "deptId", nullable = true)
	private Dept dept;

	public Emp() {}
	
	public Emp(String f_Name, String l_Name, int contactNo, Dept dept) {
		super();
		this.f_Name = f_Name;
		this.l_Name = l_Name;
		this.contactNo = contactNo;
		this.dept = dept;
	}
	public Emp(String f_Name, String l_Name, int contactNo) {
		super();
		this.f_Name = f_Name;
		this.l_Name = l_Name;
		this.contactNo = contactNo;
	}


	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getF_Name() {
		return f_Name;
	}

	public void setF_Name(String f_Name) {
		this.f_Name = f_Name;
	}

	public String getL_Name() {
		return l_Name;
	}

	public void setL_Name(String l_Name) {
		this.l_Name = l_Name;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	
	
}
