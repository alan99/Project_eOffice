package com.alan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.model.User;
import com.alan.model.Dept;
import com.alan.model.Emp;
import com.alan.model.LeaveMeetingForm;
import com.alan.model.RoomTicket;
import com.alan.repo.DeptRepo;
import com.alan.repo.EmpRepo;
import com.alan.repo.LeaveMeetingRepo;
import com.alan.repo.RoomTicketRepo;
import com.alan.repo.UserRepo;

@Service
public class AdminService {
	@Autowired
	private RoomTicketRepo roomTicketRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private DeptRepo deptRepo;
	@Autowired
	private LeaveMeetingRepo leaveMeetingRepo;
	@Autowired
	private MailService mailService;
	@Autowired
	private EmpService empService;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	public List<Emp> listAllEmps(){
		return empRepo.findAll();
	}
	
	public Emp addEmp(Emp emp) {
		Dept dept = deptRepo.findById(emp.getDept().getDeptId()).orElse(null);
		emp.setDept(dept);
//		Emp newEmp = new Emp(emp.getF_Name(), emp.getL_Name(), emp.getContactNo(), emp.getUsername(), dept);
		empRepo.save(emp);
		
		String receiver = emp.getUsername();
		String subject = "Welcome! Please Register";
		String content = "Register Link";
		
		mailService.autoSendingEmail(receiver, subject, content);
		
		return emp;
	}
	
	public void authorizeAdminRight(Emp emp) {
		String username = emp.getUsername();
		userDetailsService.authorizeAdmin(username);
	}
	
	public Emp updateEmp(Emp newEmp) {
		Emp oldEmp = empRepo.findById(newEmp.getEmpId()).orElse(null);
		
		if (oldEmp == null) {
			return null;
		} else {
			if (newEmp.getF_Name() != null)		oldEmp.setF_Name(newEmp.getF_Name());
			if (newEmp.getL_Name() != null)		oldEmp.setL_Name(newEmp.getL_Name());
			if (newEmp.getContactNo() != 0)		oldEmp.setContactNo(newEmp.getContactNo());
			
//			if (newEmp.getUsername() != null)	oldEmp.setUsername(newEmp.getUsername());
			
			Dept dept = deptRepo.findById(newEmp.getDept().getDeptId()).orElse(null);
			if (newEmp.getDept() != null)		oldEmp.setDept(dept);
			
			return empRepo.save(oldEmp);
		}
	}
	
	public void respondEmpLeave(LeaveMeetingForm form) {
		LeaveMeetingForm updatedForm = leaveMeetingRepo.findById(form.getFormId()).orElse(null);
		updatedForm.setRespond(form.getRespond());
		
		leaveMeetingRepo.save(updatedForm);
		
		String msg = "Your request for leaving is " + updatedForm.getRespond();
		empService.message(updatedForm, msg, updatedForm.getEmp());
	}
	
	public Emp removeEmp(long id) {
		Emp emp = empRepo.findById(id).orElse(null);
		
		if (emp == null) {
			return null;
		} else {

			User user = userRepo.findByUsername(emp.getUsername());
			
			empRepo.deleteById(emp.getEmpId());
			userRepo.deleteById(user.getId());
			
			return emp;
		}
	}
	
	
	
	public RoomTicket updateRoomTicket(RoomTicket ticket) {
		String updatedStatus = ticket.getStatus();
		RoomTicket updateTicket = roomTicketRepo.findById(ticket.getTicketId()).orElse(null);
		updateTicket.setStatus(updatedStatus);
		
		String receiver = ticket.getEmp().getUsername();
		String subject = "Status update";
		String content = "Please check";
		
		mailService.autoSendingEmail(receiver, subject, content);
		
		return roomTicketRepo.save(updateTicket);
	}
	
	
	
	
	
}
