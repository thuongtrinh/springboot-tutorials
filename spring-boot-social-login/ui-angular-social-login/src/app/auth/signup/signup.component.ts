import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms'; 
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL } from 'src/app/constants/api-constant';
import { Signup } from 'src/app/models/Signup';
import { APIUtils } from 'src/app/utils/ApiUtils';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  readonly VAR_GOOGLE_AUTH_URL = GOOGLE_AUTH_URL;
  readonly VAR_FACEBOOK_AUTH_URL = FACEBOOK_AUTH_URL;
  readonly VAR_GITHUB_AUTH_URL = GITHUB_AUTH_URL;

  constructor(private apiUtils: APIUtils, private router: Router, private toastr: ToastrService) { }

  loginForm = new FormGroup({
    name: new FormControl(),
    email: new FormControl(),
    password: new FormControl()
  });

  ngOnInit(): void {
  }

  handleSubmit() {
    const name = this.loginForm.get('name')?.value;
    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;

    const signUpRequest = new Signup(
      name,
      email,
      password
    );

    this.apiUtils.signup(signUpRequest)
    .subscribe(response => {
        this.toastr.success("You're successfully registered. Please login to continue!");
        this.router.navigate(['/login']);
    },
    error => {
        this.toastr.error("Oops! Something went wrong. Please try again!");
    });
  }

}
