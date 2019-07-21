import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon'

import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { AddEmployeeComponent } from '../add-employee/add-employee.component';
import { Router } from '@angular/router';

import { HttpClientService } from '../service/http-client.service';
import { MatSort, MatTableDataSource } from '@angular/material';
import { Emp, Dept } from '../model/model.component';



@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})



export class EmployeeComponent implements OnInit {
  emps: Emp[];
  displayedColumns: string[] = ['id', 'name', 'contactNo', 'dept', 'actionsColumn'];

  constructor(private httpClientService: HttpClientService,
    private dialog: MatDialog,
    private loginService: AuthenticationService
  ) { }

  // @ViewChild(MatSort, {static: true}) sort: MatSort;
  

  ngOnInit() {
    this.httpClientService.getEmps().subscribe(response => this.successResponse(response));
  }

  // ngAfterViewInit(): void {
  //   this.dataSource.sort = this.sort;
  // }

  openEditEmpDialog(emp: Emp): void {
    const dialogRef = this.dialog.open(EditEmployeeComponent, {
      width: '450px',
      data: emp
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openAddEmpDialog(): void {
    let emp:Emp = {
      empId: 0,
      f_Name: '',
      l_Name: '',
      contactNo: 0,
      username: '',
      dept: null
    }
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
      width: '450px',
      data: emp
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  public deleteEmp(emp): void {
    this.httpClientService.deleteEmp(emp)
      .subscribe(data => {
        this.emps = this.emps.filter(a => a !== emp)
      });
  }

  successResponse(response) {
    return this.emps = response;
  }

}

//========================================================================

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
})
export class EditEmployeeComponent {

  dept: Dept; 
  depts: Dept[];
  emp: Emp; 
  selectedDeptId: number;

  constructor(
    private router:Router,
    private httpClientService: HttpClientService,
    public dialogRef: MatDialogRef<EditEmployeeComponent>,
    @Inject(MAT_DIALOG_DATA) public c_emp: Emp) 
    {
      this.emp = c_emp;
      this.dept = this.emp.dept;
      this.selectedDeptId = this.dept.deptId;
      console.log(this.emp);
    }

  ngOnInit() {
    this.httpClientService.getDepts().subscribe(response => this.successResponse(response));
  }

  successResponse(response) {
    return this.depts = response;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  editEmpClick(): void {
    // console.log(this.dept);
    this.dept.deptId = this.selectedDeptId;
    this.emp.dept = this.dept;
    // console.log(this.emp);
    this.httpClientService.editEmp(this.emp)
      .subscribe(data=>{
          this.dialogRef.close();
          this.router.navigate(["/emps"]);}
      );

    this.dialogRef.close();
  }
}