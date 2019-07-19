import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AddDepartmentComponent } from '../add-department/add-department.component';
import { MatDialog } from '@angular/material';

import { AuthenticationService } from '../service/authentication.service';
import { HttpClientService } from '../service/http-client.service';

export class Dept {
  constructor(public deptId: number,
    public deptName: string) { }
}

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {
  url:string;
  depts:Dept[];

  constructor(private httpClientService: HttpClientService,
              private dialog: MatDialog,
              private loginService: AuthenticationService) { }

  ngOnInit() {
    this.httpClientService.getDepts().subscribe(response => this.successResponse(response));
  }

  successResponse(response) {
    return this.depts = response;
  }

  openAddDeptDialog(dept: Dept): void {
    const dialogRef = this.dialog.open(AddDepartmentComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
