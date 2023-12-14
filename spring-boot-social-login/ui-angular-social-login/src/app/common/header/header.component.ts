import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { ACCESS_TOKEN } from 'src/app/constants/api-constant';
import { APIUtils } from 'src/app/utils/ApiUtils';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public authenticated$: Observable<boolean> | undefined;

  constructor(private apiUtils: APIUtils, private router: Router) {
    this.authenticated$ = new BehaviorSubject(false);
   }

  ngOnInit(): void {
    this.apiUtils.authenticatedBehavior$.subscribe(auth$ => this.authenticated$ = auth$);

    if (localStorage.getItem(ACCESS_TOKEN)) {
      this.apiUtils.getCurrentUser().subscribe(response => {
        this.apiUtils.setAuthenticated(true);
      },
      error => {
        console.error("Header getCurrentUser error: ", error)
      });
    }
  }

  logout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.router.navigate([ '/login' ]);
  }
}
