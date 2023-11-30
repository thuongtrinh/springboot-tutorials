import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InformMessage } from 'src/app/enums/inform-message';
import { PasswordUpdate } from 'src/app/models/PasswordUpdate';
import { AuthService } from 'src/app/services/Auth.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {

  errorMsg = ""
  token = "";
  updatePasswordForm = new FormGroup({
    token: new FormControl(),
    oldPassword: new FormControl(),
    password: new FormControl(),
    matchPassword: new FormControl(),
  });

  constructor(private authService: AuthService, 
    private activatedRoute: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params) => {
      this.token = params['token'] || '';
    });
  }

  onFormSubmit() {
    const token = this.token ;
    const oldPassword = this.updatePasswordForm.get('oldPassword')?.value;
    const password = this.updatePasswordForm.get('password')?.value;
    const matchPassword = this.updatePasswordForm.get('matchPassword')?.value;

    const updatePass = new PasswordUpdate(
      token,
      oldPassword,
      password,
      matchPassword
    );

    this.authService.updatePassword(updatePass).subscribe(response => {
        console.log('Response OK: ' + response);
        this.authService.logoutUser()
        this.router.navigate(['/inform-success', InformMessage.SUCCESS_UPDATE_PASS]);
      },
      err => {
        this.errorMsg = "Update password failed. Check your input please !";
        console.log(err);
      }
    );
  }
}
