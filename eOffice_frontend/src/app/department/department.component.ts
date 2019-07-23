import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AddDepartmentComponent } from './add-department/add-department.component';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AuthenticationService } from '../service/authentication.service';
import { HttpClientService } from '../service/http-client.service';
import { Dept } from '../model/model.component';
import { EditDepartmentComponent } from './add-department/edit-department.component';
import { MatIconModule } from '@angular/material/icon'


@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'actionsColumn'];
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

  openAddDeptDialog(): void {
    let dept:Dept = {deptId: 0, deptName: ''}
    const dialogRef = this.dialog.open(AddDepartmentComponent, {
      width: '450px',
      data: dept
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openEditDeptDialog(dept: Dept): void {
    const dialogRef = this.dialog.open(EditDepartmentComponent, {
      width: '450px',
      data: dept
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
