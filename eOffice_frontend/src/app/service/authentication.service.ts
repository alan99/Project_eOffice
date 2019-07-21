import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

// export class User {
//   constructor(public status: string) { }
// }

// export class JwtResponse {
//   constructor(public token: string, public role: string) { }
// }
export interface JwtResponse {
  token: string;
  role: string;
}

// export class JwtRequest {
//   constructor(public username: string, public password: string) { }
// }

export interface JwtRequest {
  username: string;
  password: string;
}

const HttpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  // request:JwtRequest;

  authenticate(id: string, pw: string) {
    sessionStorage.setItem('username', id);
    // let request = new JwtRequest(username, password);
    let request:JwtRequest = {username:id, password:pw};


    return this.response(request);
    // return this.response(request);

  }

  private response(request: JwtRequest): Observable<any> {
    return this.httpClient.post<JwtResponse>('http://localhost:8081/authenticate', request, HttpOptions);
  }
  
  isUserLoggedIn() {
    let user = sessionStorage.getItem('username');
    return !(user == null);
  }

  isAdmin() {
    return sessionStorage.getItem('role')=="ADMIN";
  }

  logOut() {
    sessionStorage.removeItem('username');
  }
}
