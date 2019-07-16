import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { HttpClientService } from '../service/http-client.service';

export class Dept{
  constructor(public deptId:number){}
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
  constructor(private httpClient:HttpClient) { }

  ngOnInit() {
  }
  public createEmp(emp, dept){
    // this.emp = emp
    emp.dept = dept;
    console.log(this.emp);
    return this.httpClient.post<Emp>('http://localhost:8081/admin/update-emp', emp)
    .subscribe(data=>{
          console.log('Emp created successfully...');
    })
  }

  // createEmp():void{
  //   this.httpClient.createEmp(this.emp)
  //   .subscribe(data=>{
  //     console.log('Emp created successfully...');
  //   });
  // }
}
