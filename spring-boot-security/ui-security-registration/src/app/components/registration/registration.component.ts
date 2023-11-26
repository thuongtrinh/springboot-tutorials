import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { InformMessage } from 'src/app/enums/inform-message';
import { Registration } from 'src/app/models/Registration';
import { AuthService } from 'src/app/services/Auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  constructor(private authService: AuthService, 
    private router: Router) {}

  errorMsg = '';
  regitrationForm = new FormGroup({
    firstname: new FormControl(),
    lastname: new FormControl(),
    username: new FormControl(),
    email: new FormControl(),
    birthdate: new FormControl(),
    password: new FormControl(),
    matchPassword: new FormControl(),
    isUsing2FA: new FormControl(),
  });

  ngOnInit(): void {}

  onFormSubmit() {
    const username = this.regitrationForm.get('username')?.value;
    const firstname = this.regitrationForm.get('firstname')?.value;
    const lastname = this.regitrationForm.get('lastname')?.value;
    const email = this.regitrationForm.get('email')?.value;
    const birthdate = this.regitrationForm.get('birthdate')?.value;
    const password = this.regitrationForm.get('password')?.value;
    const matchPassword = this.regitrationForm.get('matchPassword')?.value;
    const isUsing2FA = this.regitrationForm.get('isUsing2FA')?.value;

    const registration = new Registration(
      username,
      firstname,
      lastname,
      email,
      birthdate,
      password,
      matchPassword,
      isUsing2FA
    );

    this.authService.registration(registration).subscribe(response => {
        console.log('Response OK: ' + response);
        this.router.navigate(['/inform-success', InformMessage.SUCCESS_REGISTRATION]);
      },
      err => {
        this.errorMsg = "Registration failed. Check your input please !";
        console.log(err);
        setTimeout(() => { this.errorMsg = '';  }, 5000);
      }
    );
  }
}
