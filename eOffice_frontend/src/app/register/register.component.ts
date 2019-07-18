import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

export class Request {
  constructor(public username: string, public password: string) { }
}

const HttpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username = '';
  password = '';

  constructor(private router:Router, private httpClient: HttpClient) { }

  ngOnInit() {
  }

  register() {
    let request = new Request(this.username, this.password);
    // console.log(request);
    this.httpClient.post("http://localhost:8081/emp/register", request, HttpOptions).subscribe(
      data => {
        console.log(request);
      }
    );
    this.router.navigate(["/login"]);
  }
}
