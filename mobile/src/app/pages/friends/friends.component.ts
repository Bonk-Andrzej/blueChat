import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit {

    friends = [
        {
            'name': 'Java Poz 9',
        },
        {
            'name': 'random',
        },
        {
            'name': 'off topic',
        }
    ];

    ngOnInit() {}

    constructor(private router: Router) {
    }

    createGroup() {
        this.router.navigateByUrl('/create-group');
    }
}
