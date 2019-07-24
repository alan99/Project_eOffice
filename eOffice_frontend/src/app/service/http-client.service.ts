import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { AuthenticationService } from './authentication.service';
import { Emp, Dept, Task } from '../model/model.component';

@Injectable({
  providedIn: 'root'
})
export class User{
  constructor(public username:string,
              public password:string,
              public role:string){}
}

export class HttpClientService {

  url = 'http://localhost:8081';

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
    return this.httpClient.delete<Emp>(this.url + '/update-emp/' + emp.empId);
  }
  

  public getDepts() {
    return this.httpClient.get<Dept[]>(this.url + '/depts');
  }

  public createDept(dept){
    return this.httpClient.post<Dept>(this.url + '/add-dept', dept);
  }

  public editDept(dept){
    return this.httpClient.put<Dept>(this.url + '/update-dept', dept);
  }


  public getTasks(){
    return this.httpClient.get<Task[]>(this.url + '/tasks');
  }

  public getEmpTasks(emp){
    return this.httpClient.post<Task[]>(this.url + '/tasks', emp);
  }

  public createTask(task){
    return this.httpClient.post<Task>(this.url + '/assign-task', task);
  }
}
