import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { HttpClientService } from '../service/http-client.service';

export class Dept{
  constructor(public deptId:number, 
              public deptName:string){}
}
export class Emp{
  constructor(public empId:number, 
              public f_Name:string, 
              public l_Name:string, 
              public contactNo:number, 
              public username:string, 
              public dept:Dept) {}
}
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  emps:Emp[];
  constructor(private httpClient:HttpClient) { }

  ngOnInit() {
    this.getEmps().subscribe(response=>this.successResponse(response));  
  }

  public getEmps(){
    return this.httpClient.get<Emp[]>('http://localhost:8081/admin/emps');
  }

  public deleteEmp(emp){
    return this.httpClient.delete<Emp>('http://localhost:8081/admin/update-emp/'+emp.empId)
    .subscribe(data=>{
      this.emps=this.emps.filter(a=>a !== emp)});
  }
  
  private successResponse(response): Observable<any>{
    return this.emps=response;
  }

  // deleteEmp(emp:Emp):void {
  //   this.httpClientService.deleteEmp(emp)
  //   .subscribe(data=>{
  //     this.emps=this.emps.filter(a=>a !== emp)})
  // }

}
