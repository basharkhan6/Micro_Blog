import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {finalize} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  apiBase = 'http://localhost:8080/api/';
  apiLogout = 'http://localhost:8080/logout';
  authenticated = false;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  authenticate(credentials, callback) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    // console.log("Sending HTTP Get request.");
    // console.log(credentials ? {
    //   authorization : 'Basic ' + credentials.username + ':' + credentials.password} : "No Credential");

    this.http.get(this.apiBase + 'user', {headers: headers}).subscribe(response => {
      // console.log("Request sent")
      if (response['name']) {
        this.authenticated = true;
        sessionStorage.setItem('cred', 'Basic ' + btoa(credentials.username + ':' + credentials.password));
        // console.log("Authenticated");
      } else {
        this.authenticated = false;
        // console.log("Can not Authenticate!");
      }

      return callback && callback();
    },
      error => {
        alert('Invalid Login');
    });
  }

  logout(): void {
    this.http.post(this.apiLogout, {}).pipe(
      finalize(() => {
        this.authenticated = false;
        sessionStorage.clear();
        this.router.navigateByUrl('/login');
      })).subscribe();
  }

}
