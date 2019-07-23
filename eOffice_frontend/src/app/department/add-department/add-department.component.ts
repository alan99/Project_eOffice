import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';

import { HttpClientService } from '../../service/http-client.service';
import { Dept } from '../../model/model.component';



@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})

export class AddDepartmentComponent implements OnInit {
  dept:Dept;

  constructor(private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<AddDepartmentComponent>,
              @Inject(MAT_DIALOG_DATA) public cdept: Dept) {
                this.dept = cdept;
                console.log(this.dept);
             }

  ngOnInit() {}
  

  public createDept(){
    console.log(this.dept);
    // this.dept = dept;
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
