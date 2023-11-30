import { Injectable } from '@angular/core';
import { Registration } from '../models/Registration';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PasswordUpdate } from '../models/PasswordUpdate';
import { AuthUser } from '../models/AuthUser';
import { Login } from '../models/Login';
import { ApiResponse } from '../models/common/ApiResponse';
import { AccessToken } from '../models/AccessToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  setloggedIn(tokenResponse: AccessToken) {
    console.log(tokenResponse)

    localStorage.setItem('isloggedIn', "true");
    localStorage.setItem('access_token', tokenResponse.access_token);
    localStorage.setItem('refresh_token', tokenResponse.refresh_token);
  }

  getLoggedIn() : boolean {
    return localStorage.getItem("isloggedIn") === "true";
  }

  getAccessToken(): string {
    return localStorage.getItem('access_token') || '';
  }

  login(loginData: Login) {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<ApiResponse<AccessToken>>(`${environment.urls.auth}/login`, loginData, {
      headers: httpHeader,
      observe: 'response'
    });
  }

  registration(registration: Registration) {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<ApiResponse<any>>(`${environment.urls.auth}/registration`, registration, {
      headers: httpHeader,
      observe: 'response'
    });
  }

  registrationConfirm(auth: AuthUser) {
    let httpHeaders = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get<ApiResponse<any>>(`${environment.urls.auth}/registration-confirm?token=${auth.token}`, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }

  resetPassword(auth: AuthUser) {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<ApiResponse<any>>(`${environment.urls.auth}/reset-password?email=${auth.email}`, {
      headers: httpHeader,
      observe: 'response'
    });
  }

  savePassword(passwordRequest: PasswordUpdate) {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<ApiResponse<any>>(`${environment.urls.auth}/save-password`, passwordRequest, {
      headers: httpHeader,
      observe: 'response'
    });
  }

  updatePassword(passwordRequest: PasswordUpdate) {
    const access_token = localStorage.getItem('access_token')
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
    // const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json', Authorization: `Bearer ${access_token}` },);

    return this.http.post<ApiResponse<any>>(`${environment.urls.auth}/update-password`, passwordRequest, {
      headers: httpHeader,
      observe: 'response'
    });
  }

  resendRegistrationToken(auth: AuthUser) {
    let httpHeaders = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get<Registration>(`${environment.urls.auth}/resend-registration-token?token=${auth.token}`, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }

  logoutUser(): void {
    localStorage.removeItem('isloggedIn');
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
  }
}
