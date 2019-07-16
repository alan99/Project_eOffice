import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserDTO{
  constructor(public username:string,
              public password:string,
              public role:string){}
}

export class Employee{
  constructor(public empId:number, public empName:string, public empSal:number){};
}
export class HttpClientService {

  constructor(private httpClient:HttpClient) { }

  // public getEmps(){
  //   return this.httpClient.get<Emp[]>('http://localhost:8081/emps');
  // }

  // public createEmp(emp){
  //   return this.httpClient.post<Emp>('http://localhost:8081/emps', emp);
  // }
  
  // public deleteEmp(emp){
  //   return this.httpClient.delete<Emp>('http://localhost:8081/'+emp.empId);
  // }
  
}
