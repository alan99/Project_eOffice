import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class User{
  constructor(public username:string,
              public password:string,
              public role:string){}
}

export class Dept {
  constructor(public deptId: number,
              public deptName: string) { }
}

export class Emp{
  constructor(public empId: number,
              public f_Name: string,
              public l_Name: string,
              public contactNo: number,
              public username: string,
              public dept: Dept) { }
}
export class HttpClientService {

  url = 'http://localhost:8081';
  // emp:Emp;

  constructor(private httpClient:HttpClient, private loginService:AuthenticationService) {
    if (loginService.isAdmin()){
      this.url += '/admin';
    } else {
      this.url += '/emp';
    }
   }

  public getEmps(){
    return this.httpClient.get<Emp[]>(this.url + '/emps');
  }

  public createEmp(emp){
    return this.httpClient.post<Emp>(this.url + '/add-emp', emp);
  }
  
  public editEmp(emp){
    return this.httpClient.put<Emp>(this.url + '/update-emp', emp);
  }

  public deleteEmp(emp){
    return this.httpClient.delete<Emp>(this.url + '/update-emp' + emp.empId);
  }
  

  public getDepts() {
    return this.httpClient.get<Dept[]>(this.url + '/depts');
  }

  
}
