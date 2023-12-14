import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ACCESS_TOKEN, API_BASE_URL } from '../constants/api-constant';
import { Login } from '../models/Login';
import { ApiResponse } from '../models/ApiResponse';
import { AccessToken } from '../models/AccessToken';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class APIUtils {
  
  authenticatedBehavior$: BehaviorSubject<Observable<boolean> | undefined>;
  authenticated$: Observable<boolean> | undefined;

  constructor(private http: HttpClient) {
    this.authenticatedBehavior$ = new BehaviorSubject(this.authenticated$);
  }

  setAuthenticated(auth: boolean) {
    this.authenticated$ = new BehaviorSubject(auth);
    this.authenticatedBehavior$.next(this.authenticated$);
  }

  requestHeaders(): HttpHeaders {
    let httpHeader = new HttpHeaders();
    httpHeader = httpHeader.append('Content-Type', 'application/json');

    if (localStorage.getItem(ACCESS_TOKEN)) {
      httpHeader = httpHeader.set('Authorization', `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`)
    }
    
    console.log(httpHeader)
    return httpHeader;
  }

  login(loginRequest: Login) {
    const httpHeader = this.requestHeaders();
    return this.http.post<ApiResponse<AccessToken>>(
      API_BASE_URL + '/auth/login',
      loginRequest,
      {
        headers: httpHeader,
        observe: 'response',
      }
    );
  }

  signup(loginRequest: Login) {
    const httpHeader = this.requestHeaders();
    return this.http.post<ApiResponse<any>>(
      API_BASE_URL + '/auth/signup',
      loginRequest,
      {
        headers: httpHeader,
        observe: 'response',
      }
    );
  }

  getCurrentUser() {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
      console.log("'No access token set.")
      // return null;
    }

    const httpHeader = this.requestHeaders();
    return this.http.get<ApiResponse<any>>(API_BASE_URL + '/user/me', {
      headers: httpHeader,
      observe: 'response',
    });
  }
}
