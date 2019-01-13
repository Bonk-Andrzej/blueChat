import { Component } from '@angular/core';
import {UserProfileService} from './services/user-profile.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private userProfileService: UserProfileService){

  }
  title = 'mobile';
}
