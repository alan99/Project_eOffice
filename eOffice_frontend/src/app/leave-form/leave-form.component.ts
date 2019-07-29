import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { LeaveForm, Emp } from '../model/model.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-leave-form',
  templateUrl: './leave-form.component.html',
  styleUrls: ['./leave-form.component.css']
})
export class LeaveFormComponent implements OnInit {
  form:LeaveForm;
  admin:Emp;
  emp:Emp;
  emps:Emp[];
  sDate = new Date;
  eDate = new Date;

  leaveTypes: string[] = ["Vacation", "Sickness", "Holiday", "Earn Leave", "Others"];
  
  constructor(private datePipe: DatePipe,
              private httpClientService: HttpClientService,
              private loginService: AuthenticationService,
              private router: Router) {
                this.form = {
                    formId: 0,
                    leaveType: '',
                    startDate: '',
                    endDate: '',
                    reason: '',
                    respond: 'Requesting',
                    emp: null,
                    admin: null
                }
                this.emp = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: sessionStorage.getItem('username'),
                  dept: null
                }
                this.admin = {
                  empId: 0,
                  f_Name: '',
                  l_Name: '',
                  contactNo: 0,
                  username: '',
                  dept: null
                }
                this.form.emp = this.emp;
              }

  ngOnInit() {
    this.httpClientService.getEmps().subscribe(response => this.successResponse(response));
  }

  successResponse(response) {
    return this.emps = response;
  }

  public sendForm(){
    this.form.admin = this.admin;

    this.form.startDate = this.datePipe.transform(this.sDate, "yyyy-MM-dd");
    this.form.endDate = this.datePipe.transform(this.eDate, "yyyy-MM-dd");

    console.log(this.form);

    this.httpClientService.sendForm(this.form)
    .subscribe(data=>{
          console.log(data);
          this.router.navigate(["/leaves"]);
    })
  }
}
