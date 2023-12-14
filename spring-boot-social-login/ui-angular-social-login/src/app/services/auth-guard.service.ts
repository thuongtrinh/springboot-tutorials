import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, } from '@angular/router';
import { ACCESS_TOKEN } from '../constants/api-constant';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const url = state.url;
    console.log(state);
    console.log('Url canActivate: ' + url);

    if (localStorage.getItem(ACCESS_TOKEN)) {
      return true;
    }

    this.router.navigate(["/login"]);
    return true;
  }

}
