import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ACCESS_TOKEN } from 'src/app/constants/api-constant';

@Component({
  selector: 'app-oauth2',
  templateUrl: './oauth2.component.html',
  styleUrls: ['./oauth2.component.css'],
})
export class Oauth2Component implements OnInit {

  constructor(private router: Router, private activatedroute: ActivatedRoute) {}

  ngOnInit(): void {
    const token = this.getUrlParameter('token');
    const error = this.getUrlParameter('error');

    if (token) {
      localStorage.setItem(ACCESS_TOKEN, token);
      this.router.navigate(['/profile']);
    } else {
      this.router.navigate(['/login'], {
        queryParams: {
          error: error,
        },
        relativeTo: this.activatedroute,
      });
    }
  }

  getUrlParameter(name: string) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

    var results = regex.exec(location.search);
    // var results = regex.exec(this.props.location.search);

    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
  }

}
