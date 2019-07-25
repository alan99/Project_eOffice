import { Component, OnInit } from '@angular/core';
import { Task, Emp } from '../model/model.component';
import { HttpClientService } from '../service/http-client.service';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from '../service/authentication.service';
import { AddTaskComponent } from './add-task/add-task.component';
import { EmployeeComponent } from '../employee/employee.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  emp:Emp;
  tasks: Task[];
  displayedColumns: string[] = ['id', 'name', 'startDate', 'endDate', 'leader', 'emp', 'status', 'actionsColumn'];
  
  constructor(private httpClientService: HttpClientService,
              private dialog: MatDialog,
              private loginService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    if (this.loginService.isAdmin()){
      this.httpClientService.getTasks().subscribe(response => this.successResponse(response));
    } else {
      this.emp = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: sessionStorage.getItem('username'),
                  dept: null
            }
      this.httpClientService.getEmpTasks(this.emp).subscribe(response => this.successResponse(response));
    }
    
  }

  successResponse(response) {
    return this.tasks = response;
  }

  openAddTaskDialog(): void {
    let task:Task = { taskId: 0,
                      taskName: '',
                      taskText: '',
                      startDate: '',
                      endDate: '',
                      leader: null,
                      emp: null,
                      taskStatus: '' }

    const dialogRef = this.dialog.open(AddTaskComponent, {
      width: '600px',
      data: task
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  public respondTask(task): void {
    if (this.loginService.isAdmin()){
      task.taskStatus = "Approved";
    } else {
      task.taskStatus = "Finished";
    }
    
    console.log(task);
    this.httpClientService.updateTask(task).subscribe();
  }

  

  public deleteTask(task): void {
    this.httpClientService.deleteTask(task)
      .subscribe(data => {
        this.tasks = this.tasks.filter(a => a !== task);
        this.router.navigate(["/tasks"]);
      });
  }
}
