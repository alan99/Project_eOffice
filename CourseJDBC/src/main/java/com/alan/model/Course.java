package com.alan.model;

import javax.persistence.*;

@Entity
@Table(name = "courses_curd")
public class Course {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long cId;
	
	@Column
	private String cName;

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Course(long cId, String cName) {
		super();
		this.cId = cId;
		this.cName = cName;
	}

	public Course(String cName) {
		super();
		this.cName = cName;
	}

	public Course() {}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + "]";
	}
	
	

}
