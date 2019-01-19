import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

    hamMenu() {
        this.router.navigateByUrl('/home-menu');
    }

    public openGithub(){
        window.open('https://github.com/newBlueChat/blueChat', '_blank');
    }
}
