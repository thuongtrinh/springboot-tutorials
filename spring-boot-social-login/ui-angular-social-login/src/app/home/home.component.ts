import { Component, OnInit } from '@angular/core';
import { APIUtils } from '../utils/ApiUtils';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private apiUtils: APIUtils) { }

  ngOnInit(): void {
    this.apiUtils.setAuthenticated(true);
  }

}
