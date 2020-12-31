import {AuthenticationService} from './authentication.service';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class XhrInterceptor implements HttpInterceptor {

  constructor(private auth: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // console.log("Intercepting...")
    if (this.auth.authenticated !== false) {
      // console.log("Applying Intercept header..")
      // console.log(sessionStorage.getItem("cred"))
      const xhr = req.clone({
        // withCredentials: true,
        headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
          .set('authorization', sessionStorage.getItem('cred'))
      });
      // console.log(xhr)
      return next.handle(xhr);
    }
    else {
      // console.log("No Credential")
      return next.handle(req);
    }
  }
}
