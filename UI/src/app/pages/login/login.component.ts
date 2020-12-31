import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../service/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error = '';
  credentials = {username: '', password: ''};
  redirectUrl: string;

  constructor(
    private auth: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.redirectUrl = this.route.snapshot.queryParams['redirectUrl'] || '/';
  }

  login(): boolean {
    this.auth.authenticate(this.credentials, () => {
      this.router.navigateByUrl(this.redirectUrl);
    });
    return false;
  }

}
