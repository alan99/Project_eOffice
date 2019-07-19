import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';

import { HttpClientService } from '../service/http-client.service';

export class Dept {
  constructor(public deptName: string) { }
}

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})

export class AddDepartmentComponent implements OnInit {
  dept:Dept = new Dept('');

  constructor(private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<AddDepartmentComponent>) { }

  ngOnInit() {}
  

  public createDept(){
    console.log(this.dept);
    return this.httpClientService.createDept(this.dept)
    .subscribe(data=>{
          console.log('Dept created successfully...');
          this.dialogRef.close();
          this.router.navigate(["/depts"]);
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
