import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InformMessage } from 'src/app/enums/inform-message';
import { AuthUser } from 'src/app/models/AuthUser';
import { AuthService } from 'src/app/services/Auth.service';

@Component({
  selector: 'app-inform-error',
  templateUrl: './inform-error.component.html',
})
export class InformErrorComponent implements OnInit {
  errorMsg = '';
  errorId = '';
  token = '';

  constructor(
    private activatedroute: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.activatedroute.queryParams.subscribe((params) => {
      this.errorId = params['id'] || '';
      this.token = params['token'] || '';
    });

    if (this.errorId == InformMessage.ERROR_REGISTRATION_CONFIRM_TOKEN) {
      this.errorMsg =
        'Token error, Please to mail box to check link acive account !';
    } else if (this.errorId == InformMessage.ERROR_RESEND_MAIL_ACTIVE_ACCOUNT) {
      this.errorMsg = 'Token expired, Please resend mail to active account !';
    }
  }

  resendMailActive() {
    alert(this.token)
    const authUser = new AuthUser(this.token, '');
    this.authService.resendRegistrationToken(authUser).subscribe(
      (response) => {
        console.log('Response registrationConfirm OK: ' + response);
        this.router.navigate([
          '/inform-success',
          InformMessage.SUCCESS_REGISTRATION,
        ]);
      },
      (err) => {
        this.errorId == InformMessage.ERROR_RESEND_MAIL_ACTIVE_ACCOUNT;
        this.errorMsg = 'Resend mail to active account failed, retry please !';
      }
    );
  }
}
