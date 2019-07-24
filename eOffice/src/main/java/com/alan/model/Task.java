package com.alan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "eoffice_tasks")
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long taskId;
	
	@Column
	private String taskName;
	
	@Column
	private String taskText;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date startDate;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "leaderId", nullable = false)
	private Emp leader;
	
	@ManyToOne
	@JoinColumn(name = "empId", nullable = false)
	private Emp emp;
	
	@Column
	private String taskStatus;

	
	public Task() {}

	
	public Task(String taskName, String taskText, Date startDate, Date endDate, Emp leader, Emp emp) {
		super();
		this.taskName = taskName;
		this.taskText = taskText;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leader = leader;
		this.emp = emp;
//		this.taskStatus = "Uncompleted";
	}


	public Task(String taskName, String taskText, Date startDate, Date endDate) {
		super();
		this.taskName = taskName;
		this.taskText = taskText;
		this.startDate = startDate;
		this.endDate = endDate;
		this.taskStatus = "Uncompleted";
	}

	

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public Emp getLeader() {
		return leader;
	}

	public void setLeader(Emp leader) {
		this.leader = leader;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
}
