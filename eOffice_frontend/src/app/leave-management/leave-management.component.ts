import { Component, OnInit } from '@angular/core';
import { LeaveForm, Emp } from '../model/model.component';
import { HttpClientService } from '../service/http-client.service';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-leave-management',
  templateUrl: './leave-management.component.html',
  styleUrls: ['./leave-management.component.css']
})
export class LeaveManagementComponent implements OnInit {
  forms:LeaveForm[];
  emp:Emp;
  displayedColumns: string[] = ['id', 'leaveType', 'startDate', 'endDate', 'emp', 'status', 'actionsColumn', 'confirm'];

  actions: string[] = ["Approved", "Rejected"];
  actionControl = new FormControl('', [Validators.required]);

  constructor(private httpClientService: HttpClientService,
              private dialog: MatDialog,
              private loginService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.httpClientService.getLeaveForms().subscribe(response => this.successResponse(response));
    console.log(this.forms);
  }

  successResponse(response) {
    return this.forms = response;
  }

  public respondForm(form): void {
    console.log(form);
    this.httpClientService.respondForm(form).subscribe();
  }

  
}
