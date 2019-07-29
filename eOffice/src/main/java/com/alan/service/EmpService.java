package com.alan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alan.model.Emp;
import com.alan.model.LeaveMeetingForm;
import com.alan.model.Room;
import com.alan.model.RoomTicket;
import com.alan.model.Task;
import com.alan.model.UserDTO;
import com.alan.repo.EmpRepo;
import com.alan.repo.LeaveMeetingRepo;
import com.alan.repo.RoomRepo;
import com.alan.repo.RoomTicketRepo;
import com.alan.repo.TaskRepo;

@Service
public class EmpService {
    
    @Autowired
    private RoomTicketRepo roomTicketRepo;
    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private LeaveMeetingRepo leaveMeetingRepo;
    
    
    @Autowired
    private MailService mailService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    
    public List<Emp> listAllEmps(){
		return empRepo.findAll();
	}
    
    
    
    
    public void register(UserDTO user) {
    	if (userDetailsService.findUser(user.getUsername()) == null 
    	 && empRepo.findByUsername(user.getUsername()) != null)
    		userDetailsService.register(user);
    }
    
    
    
    
    public List<Task> listAllTasks(Emp emp){
    	return taskRepo.findByEmp(empRepo.findByUsername(emp.getUsername()));
    }
    
    
    
    
    public List<RoomTicket> listAllRoomTickets(Emp emp){
    	return roomTicketRepo.findByEmp(empRepo.findById(emp.getEmpId()).orElse(null));
    }
    
    
    
    
	
	public LeaveMeetingForm leaveMeeting(LeaveMeetingForm form) {
		Emp emp= empRepo.findByUsername(form.getEmp().getUsername());				form.setEmp(emp);
		Emp admin = empRepo.findById(form.getAdmin().getEmpId()).orElse(null);		form.setAdmin(admin);
		form.setRespond("Requesting");
		
		message(form, form.getReason(), admin);
		
		return leaveMeetingRepo.save(form);
	}
	
	
	public void message(LeaveMeetingForm form, String msg, Emp receiver) {
		String subject = form.getLeaveType() + " " + form.getEmp().getF_Name() + " " + form.getEmp().getL_Name();
		
		mailService.autoSendingEmail(receiver.getUsername(), subject, msg);
	}
}
