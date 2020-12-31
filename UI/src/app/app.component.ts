import { Component } from '@angular/core';
import {AuthenticationService} from './service/authentication.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Micro Blog';
  apiLogout = 'http://localhost:8080/logout';

  constructor(
    private auth: AuthenticationService,
    private http: HttpClient,
    private router: Router) {
  }

  logout() {
    this.auth.logout();
  }

  isAuthenticated(): boolean {
    return this.auth.authenticated;
  }

}
