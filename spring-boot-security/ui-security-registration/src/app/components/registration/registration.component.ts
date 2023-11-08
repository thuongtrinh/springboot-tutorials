import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Registration } from 'src/app/models/registration';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  constructor(private authService: AuthService) {}

  loginForm = new FormGroup({
    firstname: new FormControl(),
    lastname: new FormControl(),
    email: new FormControl(),
    password: new FormControl(),
    matchPassword: new FormControl(),
    twoStepVer: new FormControl(),
  });

  ngOnInit(): void {}

  onFormSubmit() {
    const firstname = this.loginForm.get('firstname')?.value;
    const lastname = this.loginForm.get('lastname')?.value;

    const registration = new Registration(
      firstname,
      lastname,
      lastname,
      lastname,
      lastname,
      lastname
    );

    this.authService.registration(registration);
  }
}
