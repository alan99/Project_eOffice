import { Component, OnInit, Inject } from '@angular/core';
import { Emp, Task } from 'src/app/model/model.component';
import { Router } from '@angular/router';
import { HttpClientService } from 'src/app/service/http-client.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  task:Task;
  leader:Emp;
  emp:Emp;
  emps:Emp[];
  // sDate = new FormControl((new Date()).toISOString);
  // eDate = new FormControl(new Date());
  sDate = new Date;
  eDate = new Date;

  minDate = new Date;
  // datePipe:DatePipe;
  constructor(private datePipe: DatePipe,
              private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<AddTaskComponent>,
              @Inject(MAT_DIALOG_DATA) public c_task: Task) { 
                this.task = c_task;

                this.leader = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: sessionStorage.getItem('username'),
                  dept: null
                }
                this.task.leader = this.leader;

                this.emp = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: '',
                  dept: null
                }
                // this.task.emp = this.emp;
              }

  ngOnInit() {
    this.httpClientService.getEmps().subscribe(response => this.successResponse(response));
  }

  successResponse(response) {
    return this.emps = response;
  }

  myFilter = (d: Date): boolean => {
    const day = d.getDay();
    // Prevent Saturday and Sunday from being selected.
    return day !== 0 && day !== 6;
  }

  public createTask(){    
    this.task.emp = this.emp;
    console.log(this.emp);
    console.log(this.eDate);
    console.log(this.datePipe.transform(this.eDate, "yyyy-MM-dd"));
    this.task.startDate = this.datePipe.transform(this.sDate, "yyyy-MM-dd"); //this.sDate.toISOString().substring(0,10); // 
    this.task.endDate = this.datePipe.transform(this.eDate, "yyyy-MM-dd"); //this.eDate.toISOString().substring(0,10); //

    
    
    this.httpClientService.createTask(this.task)
    .subscribe(data=>{
          console.log('Task assigned successfully...');
    })
    
    this.dialogRef.close();
    this.router.navigate(["/tasks"]);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
