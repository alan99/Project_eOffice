package com.alan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.model.*;
import com.alan.repo.EmpRepo;
import com.alan.repo.TaskRepo;

@Service
public class TaskService {

	@Autowired
	private EmpRepo empRepo;
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private MailService mailService;
	
	
	
	public List<Task> findAllTasks(){
		return taskRepo.findAll();
	}
	
	public String assignTask(Task task) {
		Emp leader = empRepo.findById(task.getLeader().getEmpId()).orElse(null);
		Emp emp= empRepo.findById(task.getEmp().getEmpId()).orElse(null);
		
		if (leader != null && emp != null) {
			task.setLeader(leader);
			task.setEmp(emp);
			taskRepo.save(task);
			
			String receiver = emp.getUsername();
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
		
			return "Delivered!";
		
		} else {
			return "Please give the leader/emp";
		}
	}
	
	public void respondTask(Task task) {
		Task updatedTask = taskRepo.findById(task.getTaskId()).orElse(null);
		updatedTask.setTaskStatus(task.getTaskStatus());
		
		taskRepo.save(updatedTask);
		
		String receiver = task.getLeader().getUsername();
		String subject = "Task Modification Notification [" + task.getTaskId() + "]";
		String content = "Task " +task.getTaskId() + ": " + task.getTaskName() 
						+ " has been modified successfully please & revert if any changes.\n" 
						+ "Thanks\n" 
						+ task.getEmp().getF_Name() + "\n";
		
		mailService.autoSendingEmail(receiver, subject, content);
	}
	
}
