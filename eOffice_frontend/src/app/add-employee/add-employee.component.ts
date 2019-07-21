import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';

import { HttpClientService } from '../service/http-client.service';
import { Emp, Dept } from '../model/model.component';


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  dept:Dept;
  emp:Emp;
  depts: Dept[];
  selectedDeptId:number;

  constructor(private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<AddEmployeeComponent>,
              @Inject(MAT_DIALOG_DATA) public c_emp: Emp) 
  { 
    // this.dept = this.emp.dept;
    this.emp = c_emp;
    this.selectedDeptId = 1;
    this.dept = {deptId: this.selectedDeptId, deptName: ''}
    this.emp.dept= this.dept;
    
    console.log(this.emp);
  }

  ngOnInit() {
    this.httpClientService.getDepts().subscribe(response => this.successResponse(response));
  }
  
  successResponse(response) {
    return this.depts = response;
  }

  public createEmp(){
    console.log(this.selectedDeptId);
    console.log(this.dept);
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
