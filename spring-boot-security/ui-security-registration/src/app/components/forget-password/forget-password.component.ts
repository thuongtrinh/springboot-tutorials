import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { InformMessage } from 'src/app/enums/inform-message';
import { AuthUser } from 'src/app/models/AuthUser';
import { AuthService } from 'src/app/services/Auth.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css'],
})
export class ForgetPasswordComponent implements OnInit {

  errorMsg = '';

  constructor(private router: Router, private authService: AuthService) {}

  forgetPassForm = new FormGroup({
    email: new FormControl(),
  });

  ngOnInit(): void {}

  onFormSubmit() {
    const email = this.forgetPassForm.get('email')?.value;
    const data = new AuthUser('', email);

    this.authService.resetPassword(data).subscribe(
      (response) => {
        console.log('Response OK: ' + response);
        this.router.navigate(['/inform-success', InformMessage.SUCCESS_SEND_MAIL_RESET_PASS]);
      },
      (err) => {
        this.errorMsg = 'Send mail reset password failed !';
      }
    );
  }
}
