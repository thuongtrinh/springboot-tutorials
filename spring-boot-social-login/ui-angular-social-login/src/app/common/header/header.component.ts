import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ACCESS_TOKEN } from 'src/app/constants/api-constant';
import { APIUtils } from 'src/app/utils/ApiUtils';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public authenticated = false;

  constructor(private apiUtils: APIUtils, private router: Router) {
    if (localStorage.getItem(ACCESS_TOKEN)) {
      this.apiUtils.getCurrentUser().subscribe(response => {
        this.authenticated = true;
      },
      error => {
        console.error("Header getCurrentUser error: ", error)
      });
    }
   }

  ngOnInit(): void {
  }

  logout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.router.navigate([ '/login' ]);
  }
}
