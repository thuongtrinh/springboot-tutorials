import { Injectable } from '@angular/core';
import { Registration } from '../models/registration';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  registration(registration: Registration) {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Registration>("url", registration, {
      headers: httpHeader,
      observe: 'response'
    });
  }

}
