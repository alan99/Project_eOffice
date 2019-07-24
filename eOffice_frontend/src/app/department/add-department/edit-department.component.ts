import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';

import { HttpClientService } from '../../service/http-client.service';
import { Dept } from '../../model/model.component';


@Component({
  selector: 'app-edit-department',
  templateUrl: './edit-department.component.html',
  styleUrls: ['./add-department.component.css']
})

export class EditDepartmentComponent implements OnInit {
  dept:Dept;

  constructor(private router:Router,
              private httpClientService:HttpClientService,
              public dialogRef: MatDialogRef<EditDepartmentComponent>,
              @Inject(MAT_DIALOG_DATA) public cdept: Dept) {
                  this.dept = cdept;
                  console.log(this.dept);
               }

  ngOnInit() {}
  

  public editDeptClick(){
    // console.log(this.dept);
    return this.httpClientService.editDept(this.dept)
    .subscribe(data=>{
          console.log('Dept edited successfully...');
          this.dialogRef.close();
          // this.router.navigate(["/depts"]);
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
