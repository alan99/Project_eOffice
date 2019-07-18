import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { AddEmployeeComponent } from '../add-employee/add-employee.component';

// import { HttpClientService } from '../service/http-client.service';

export interface DialogDataDept {
  deptId: number,
  deptName: string
}
export interface DialogData {
  empId: number,
  f_Name: string,
  l_Name: string,
  contactNo: number,
  username: string,
  dept: DialogDataDept
}
export class Dept {
  constructor(public deptId: number,
    public deptName: string) { }
}
export class Emp {
  constructor(public empId: number,
    public f_Name: string,
    public l_Name: string,
    public contactNo: number,
    public username: string,
    public dept: Dept) { }
}
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  emps: Emp[];
  url = 'http://localhost:8081/admin';
  constructor(private httpClient: HttpClient,
    private dialog: MatDialog,
    private loginService: AuthenticationService
  ) { }

  ngOnInit() {
    this.getEmps().subscribe(response => this.successResponse(response));
  }

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
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  public getEmps() {
    return this.httpClient.get<Emp[]>(this.url + '/emps');
  }

  public deleteEmp(emp): void {
    this.httpClient.delete<Emp>(this.url + '/update-emp/' + emp.empId)
      .subscribe(data => {
        this.emps = this.emps.filter(a => a !== emp)
      });
  }

  successResponse(response) {
    return this.emps = response;
  }

  // deleteEmp(emp:Emp):void {
  //   this.httpClientService.deleteEmp(emp)
  //   .subscribe(data=>{
  //     this.emps=this.emps.filter(a=>a !== emp)})
  // }

}

//========================================================================

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
})
export class EditEmployeeComponent {
  url = 'http://localhost:8081/admin';

  dept: Dept = new Dept(0, '');
  depts: Dept[];
  emp: Emp = new Emp(0, '', '', 0, '', null);
  selectedDeptId: number;

  constructor(
    private httpClient: HttpClient,
    public dialogRef: MatDialogRef<EditEmployeeComponent>,
    @Inject(MAT_DIALOG_DATA) public c_emp: Emp) 
    {
      this.emp = c_emp;
      // this.emp.dept = this.dept;
      this.dept = this.emp.dept;
      this.selectedDeptId = this.dept.deptId;
      console.log(this.emp);
    }

  ngOnInit() {
    this.getDepts().subscribe(response => this.successResponse(response));
    // this.selectedDeptId = this.dept.deptId;
  }

  successResponse(response) {
    return this.depts = response;
  }

  public getDepts() {
    return this.httpClient.get<Dept[]>(this.url + '/depts');
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  editEmpClick(): void {
    console.log(this.dept);
    this.dept.deptId = this.selectedDeptId;
    this.emp.dept = this.dept;
    console.log(this.emp);
    this.httpClient.put<Emp>(this.url + '/update-emp', this.emp)
      .subscribe(
        // data=>{console.log('Emp updated successfully...');}
      );

    this.dialogRef.close();
  }
}