import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Login } from 'src/app/models/Login';
import { ActivatedRoute, Router } from '@angular/router';
import { ACCESS_TOKEN, FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL } from 'src/app/constants/api-constant';
import { AccessToken } from 'src/app/models/AccessToken';
import { ToastrService } from 'ngx-toastr';
import { APIUtils } from 'src/app/utils/ApiUtils';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  readonly VAR_GOOGLE_AUTH_URL = GOOGLE_AUTH_URL;
  readonly VAR_FACEBOOK_AUTH_URL = FACEBOOK_AUTH_URL;
  readonly VAR_GITHUB_AUTH_URL = GITHUB_AUTH_URL;
  private errorMessage = '';

  constructor(private apiUtils: APIUtils, private router: Router, private toastr: ToastrService, private activatedRoute: ActivatedRoute) { }

  loginForm = new FormGroup({
    email: new FormControl(),
    password: new FormControl()
  });

  ngOnInit(): void {
    // const error = this.activatedRoute.snapshot.paramMap.get('error') as string; // get path var

    this.errorMessage =  '';
    this.activatedRoute.queryParams.subscribe((params) => {  // get param
      this.errorMessage = params['error'] || '';
    });

    if (this.errorMessage) {
        this.toastr.error(this.errorMessage);
    }
  }

  handleSubmit() {
    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;
    const loginRequest = new Login(
      email,
      password
    );

    this.apiUtils.login(loginRequest)
    .subscribe(response => {
      console.log(response);
        let token: AccessToken = response.body as unknown as AccessToken;
        this.setloggedIn(token);
        this.toastr.success("You're successfully logged in!");
        this.router.navigate(['/home']);
    },
    error => {
        this.toastr.error("Oops! Something went wrong. Please try again!");
        // this.toastr.error(error.message, error.name, { closeButton: true });
    });
  }

  
  setloggedIn(tokenResponse: AccessToken) {
    localStorage.setItem(ACCESS_TOKEN, tokenResponse.accessToken);
  }
}
