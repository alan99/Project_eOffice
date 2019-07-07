package com.alan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.dao.EmpRepo;
import com.alan.dao.TaskRepo;
import com.alan.dao.UserDao;
import com.alan.model.*;
@Service
public class TaskService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TaskRepo taskRepo;
	
	public void assignTask(Task task, Emp leader, Emp emp) {
		task.setLeader(leader);
		task.setEmp(emp);
		taskRepo.save(task);
		
		String receiver = userDao.findByEmp(emp).getUsername();
		String subject = "Task Assignment Notification [" + task.getTaskId() + "]";
		String content = "TaskName: " + task.getTaskName() + "\n" + 
				"Description: " + task.getTaskText() + "\n" + 
				"Start Date: "+ task.getStartDate() + "\n" + 
				"End Date: "+ task.getEndDate() + "\n" + 
				"\n" + 
				"Please do before "+ task.getEndDate() +".\n" +
				"\n" + 
				"Thanks\n" + 
				"Admin \n" + 
				"Eoffice Corp.";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
	
	public void respondTask(Task task) {
		taskRepo.save(task);
		
		String receiver = userDao.findByEmp(task.getLeader()).getUsername();
		String subject = "Task Modification Notification [" + task.getTaskId() + "]";
		String content = "Task " +task.getTaskId() + ". " + task.getTaskName() + " has been modified successfully please & revert if any changes.\n" + 
				"Thanks\n" + 
				task.getEmp().getF_Name() + "\n";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
	
}
