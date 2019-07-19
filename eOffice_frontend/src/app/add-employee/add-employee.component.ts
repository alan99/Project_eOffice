import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';

import { HttpClientService } from '../service/http-client.service';

export class Dept{
  constructor(public deptId:number
              // public deptName:string
              ){}
}
export class Emp{
  constructor(public f_Name:string, 
              public l_Name:string, 
              public contactNo:number, 
              public username:string, 
              public dept:Dept) {}
}

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  dept:Dept = new Dept(0);
  emp:Emp = new Emp('','',0,'',null);
  depts: Dept[];
  selectedDeptId:number;

  constructor(private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<AddEmployeeComponent>) 
  { 
    // this.dept = this.emp.dept;
    this.selectedDeptId = 1;
  }

  ngOnInit() {
    this.httpClientService.getDepts().subscribe(response => this.successResponse(response));
  }
  
  successResponse(response) {
    return this.depts = response;
  }

  public createEmp(){
    this.dept.deptId = this.selectedDeptId;
    this.emp.dept = this.dept;
    console.log(this.emp);
    this.httpClientService.createEmp(this.emp)
    .subscribe(data=>{
          console.log('Emp created successfully...');
    })
    
    this.dialogRef.close();
    this.router.navigate(["/emps"]);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  
}
