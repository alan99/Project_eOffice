import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

export class User {
  constructor(public status: string) { }
}

export class JwtResponse {
  constructor(public token: string) { }
}

export class JwtRequest {
  constructor(public username: string, public password: string) { }
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

  // authenticate(username,password){
  //   console.log(username, password);
  //   return this.httpClient.post<any>('http://localhost:8081/authenticate', {username,password}).pipe(
  //     map (
  //       userData => {
  //         sessionStorage.setItem('username', username);
  //         // console.log(username);
  //         let tokenStr = 'Bearer ' + userData.token;
  //         console.log(tokenStr);
  //         sessionStorage.setItem('token',tokenStr);
  //         return userData;
  //       }
  //     )
  //   )
  // }

  authenticate(username: string, password: string) {
    sessionStorage.setItem('username', username);
    let request = new JwtRequest(username, password);

    return this.response(username, password, request);

    
    // myResponse.subscribe(userData => {
    //   console.log(request);
    //   
    //   let tokenStr = 'Bearer ' + userData.token;
    //   console.log(tokenStr);
    //   sessionStorage.setItem('token', tokenStr);
    // });
    // return true;

    // return this.httpClient.post<any>()    
  }

  private response(username, password, request: JwtRequest): Observable<any> {
    return this.httpClient.post<JwtResponse>('http://localhost:8081/authenticate', request, HttpOptions);
  }
  
  isUserLoggedIn() {
    let user = sessionStorage.getItem('username');
    return !(user == null);
  }

  logOut() {
    sessionStorage.removeItem('username');
  }
}
