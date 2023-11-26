import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InformMessage } from 'src/app/enums/inform-message';
import { AuthUser } from 'src/app/models/AuthUser';
import { AuthService } from 'src/app/services/Auth.service';
import { ApiResponse } from '../../models/common/ApiResponse';
import { MessageCode } from 'src/app/enums/message-code';

@Component({
  selector: 'app-registration-confirm',
  templateUrl: './registration-confirm.component.html',
  styleUrls: ['./registration-confirm.component.css'],
})
export class RegistrationConfirmComponent implements OnInit {
  token = '';
  email = '';
  result = true;

  constructor(
    private activatedroute: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.activatedroute.queryParams.subscribe((params) => {
      this.token = params['token'] || '';
    });

    if (this.token) {
      const authUser = new AuthUser(this.token, this.email);

      this.authService.registrationConfirm(authUser).subscribe(
        (response) => {
          console.log('Response registrationConfirm OK: ' + response);
        },
        (err) => {
          console.log(err);
          this.result = false;

          let responseError: ApiResponse<any> = err.error as unknown as ApiResponse<any>;
          if (MessageCode.MS001 == responseError.code) {

            this.router.navigate(['/inform-error'], {
              queryParams: {
                id: InformMessage.ERROR_RESEND_MAIL_ACTIVE_ACCOUNT,
                token: this.token,
              },
              relativeTo: this.activatedroute,
            });
          } else if (!this.result) {
            this.router.navigate(['/inform-error'], {
              queryParams: {
                id: InformMessage.ERROR_REGISTRATION_CONFIRM_TOKEN,
              },
              relativeTo: this.activatedroute,
            });
          }
        }
      );
    } else {
      this.result = false;
    }

    if (!this.result) {
      this.router.navigate(['/inform-error'], {
        queryParams: { id: InformMessage.ERROR_REGISTRATION_CONFIRM_TOKEN },
        relativeTo: this.activatedroute,
      });
    }

    console.log(this.result);
  }
}
