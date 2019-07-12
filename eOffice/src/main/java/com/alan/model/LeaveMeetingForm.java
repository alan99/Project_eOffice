package com.alan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "eoffice_leave_meeting_forms")
public class LeaveMeetingForm {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long formId;
	
	@Column
	private String leaveType;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date startDate;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date endDate;

	@Column
	private String reason;
	
	@Column
	private String respond;
	
	@ManyToOne
	@JoinColumn(name = "empId", nullable = false)
	private Emp emp;
	
	@ManyToOne
	@JoinColumn(name = "adminId", nullable = false)
	private Emp admin;

	public LeaveMeetingForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveMeetingForm(String leaveType, Date startDate, Date endDate, String reason, Emp emp, Emp admin) {
		super();
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.emp = emp;
		this.admin = admin;
		this.respond = "Requesting";
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Emp getAdmin() {
		return admin;
	}

	public void setAdmin(Emp admin) {
		this.admin = admin;
	}

	public String getRespond() {
		return respond;
	}

	public void setRespond(String respond) {
		this.respond = respond;
	}
	
	
}
