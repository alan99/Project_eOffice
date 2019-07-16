package com.alan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.dao.DeptRepo;
import com.alan.dao.EmpRepo;
import com.alan.dao.RoomTicketRepo;
import com.alan.dao.UserDao;
import com.alan.model.DAOUser;
import com.alan.model.Dept;
import com.alan.model.Emp;
import com.alan.model.RoomTicket;

@Service
public class AdminService {
	@Autowired
	private RoomTicketRepo roomTicketRepo;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private DeptRepo deptRepo;
	
	@Autowired
	private MailService mailService;
	
	
	
	public void adminUpdate(RoomTicket ticket, String updatedStatus) {
		ticket.setStatus(updatedStatus);
		roomTicketRepo.save(ticket);
		
		String receiver = ticket.getEmp().getUsername();
		String subject = "Status update";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
	
	
	public Emp addEmp(Emp emp) {
		Dept dept = deptRepo.findById(emp.getDept().getDeptId()).orElse(null);
		Emp newEmp = new Emp(emp.getF_Name(), emp.getL_Name(), emp.getContactNo(), emp.getUsername(), dept);
		
		String receiver = newEmp.getUsername();
		String subject = "Welcome! Please Register";
		String content = "Register Link";
		
		mailService.autoSendingEmail(receiver, subject, content);
		return newEmp;
	}
	
	public void updateEmp(Emp oldEmp, Emp newEmp) {
		if (newEmp.getF_Name() != null)		oldEmp.setF_Name(newEmp.getF_Name());
		if (newEmp.getL_Name() != null)		oldEmp.setL_Name(newEmp.getL_Name());
		if (newEmp.getContactNo() != 0)		oldEmp.setContactNo(newEmp.getContactNo());
		if (newEmp.getUsername() != null)	oldEmp.setUsername(newEmp.getUsername());
		
		Dept dept = deptRepo.findById(newEmp.getDept().getDeptId()).orElse(null);
		if (newEmp.getDept() != null)		oldEmp.setDept(dept);
		
		empRepo.save(oldEmp);
	}
	
	public String removeEmp(Emp emp) {
		String empFName = emp.getF_Name(), empLName = emp.getL_Name();
		DAOUser user = userDao.findByUsername(emp.getUsername());
		empRepo.deleteById(emp.getEmpId());
		userDao.deleteById(user.getId());
		return "Employee " + empFName + " " + empLName + " is removed from the database";
	}
}
