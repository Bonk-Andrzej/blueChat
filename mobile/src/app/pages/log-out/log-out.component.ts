import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { Location } from '@angular/common';

@Component({
    selector: 'app-log-out',
    templateUrl: './log-out.component.html',
    styleUrls: ['./log-out.component.scss']
})
export class LogOutComponent implements OnInit {

    constructor(private router: Router,
                private location: Location) {
    }

    ngOnInit() {
    }

    cancelHandler() {
        this.location.back();
    }

    confirmHandler() {
        this.router.navigateByUrl("/").catch();
    }

}
