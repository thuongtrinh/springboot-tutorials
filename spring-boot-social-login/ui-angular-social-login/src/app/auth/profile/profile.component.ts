import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { APIUtils } from 'src/app/utils/ApiUtils';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public currentUser: any = null;

  constructor(private router: Router, private apiUtils: APIUtils) { }

  ngOnInit(): void {

    this.apiUtils.getCurrentUser().subscribe(response => {
      this.apiUtils.setAuthenticated(true);

      console.log(response)
      this.currentUser = response.body;
    },
    error => {
      this.apiUtils.setAuthenticated(false);
      console.error("Profile getCurrentUser error: ", error);
      this.router.navigate(['/login']);
    });
  }

}
