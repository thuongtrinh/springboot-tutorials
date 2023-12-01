import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/Auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const accessToken = this.authService.getAccessToken();

    if (accessToken && accessToken !== '') {
      request = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${accessToken}`)
      });

      // let headers = new HttpHeaders()
      // headers = headers.append('Access-Control-Allow-Origin', '*')
      // headers = headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, PUT')
      // headers = headers.append('Content-Type', 'application/json')
      // headers = headers.append('Accept', 'application/json')
    }

    return next.handle(request);
  }
}
