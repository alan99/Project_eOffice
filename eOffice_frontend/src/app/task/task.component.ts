import { Component, OnInit } from '@angular/core';
import { Task, Emp } from '../model/model.component';
import { HttpClientService } from '../service/http-client.service';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from '../service/authentication.service';
import { AddTaskComponent } from './add-task/add-task.component';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks: Task[];
  displayedColumns: string[] = ['id', 'name', 'startDate', 'endDate', 'leader', 'emp', 'status'];//, 'actionsColumn'];
  
  constructor(private httpClientService: HttpClientService,
              private dialog: MatDialog,
              private loginService: AuthenticationService) { }

  ngOnInit() {
    this.httpClientService.getTasks().subscribe(response => this.successResponse(response));
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
      width: '450px',
      data: task
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
