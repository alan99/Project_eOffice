package com.alan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eoffice_dept")
public class Dept {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long deptId;
	@Column
	private String deptName;
	
	
	public Dept() {}
	
	public Dept(String deptName) {
		super();
		this.deptName = deptName;
	}


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
