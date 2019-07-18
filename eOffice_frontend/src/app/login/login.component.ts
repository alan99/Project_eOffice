import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  invalidLogin = true;
  constructor(private router:Router, private loginservice:AuthenticationService) { }

  ngOnInit() {
  }

  checkLogin(){
    this.loginservice.authenticate(this.username,this.password).subscribe(
      data => {
        sessionStorage.setItem("token",data.token);
        sessionStorage.setItem("role", data.role);
        console.log(sessionStorage.getItem("token"), sessionStorage.getItem('role'));
        this.router.navigate([""]);     // redirect to the specific page (ex: ["/emps"])
      }
    );
  }     
}
