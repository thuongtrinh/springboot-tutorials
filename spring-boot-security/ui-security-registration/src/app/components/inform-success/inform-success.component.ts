import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InformMessage } from 'src/app/enums/inform-message';

@Component({
  selector: 'app-inform-success',
  templateUrl: './inform-success.component.html',
  styles: [],
})
export class InformSuccessComponent implements OnInit {
  messageId = '';
  contentMessage = '';

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    // this.activatedRoute.params.subscribe(s => { console.log(s["id"]) });
    this.messageId = this.activatedRoute.snapshot.paramMap.get('id') as string;

    if (this.messageId == InformMessage.SUCCESS_REGISTRATION) {
      this.contentMessage =
        'You registration account successfully, please to mail box to active account !';
    } else if (this.messageId == InformMessage.SUCCESS_SEND_MAIL_RESET_PASS) {
      this.contentMessage =
        'Send mail reset password successfully, please to mail box click link to update password !';
    } else if (this.messageId == InformMessage.SUCCESS_UPDATE_PASS) {
      this.contentMessage = 'Update password successfully, please to';
    }
  }
}
