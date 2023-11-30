import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AccessToken } from 'src/app/models/AccessToken';
import { Login } from 'src/app/models/Login';
import { AuthService } from 'src/app/services/Auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMsg = '';

  constructor(private authService: AuthService, 
    private router: Router) {}

  loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    login2fa: new FormControl(),
  });

  ngOnInit(): void {
  }

  onFormSubmit() {
    const username = this.loginForm.get('username')?.value;
    const password = this.loginForm.get('password')?.value;
    const login2fa = this.loginForm.get('login2fa')?.value;

    const loginData = new Login(
      username,
      password,
      login2fa
    );

    this.authService.login(loginData).subscribe(response => {
        console.log('Response OK: ' + response.body);

        let token: AccessToken = response.body?.data as unknown as AccessToken;
        this.authService.setloggedIn(token);

        this.router.navigate(['/home']);
      },
      err => {
        this.errorMsg = "Login failed. Check info input please !";
        setTimeout(() => { this.errorMsg = '';  }, 50000);
      }
    );
  }

}
