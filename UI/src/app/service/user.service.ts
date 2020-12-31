import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  baseUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {
  }

  registerUser(user: User): Observable<any> {
    return this.http.post(this.baseUrl + 'users', user);
  }

}
