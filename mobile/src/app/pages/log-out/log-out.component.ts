import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    selector: 'app-log-out',
    templateUrl: './log-out.component.html',
    styleUrls: ['./log-out.component.scss']
})
export class LogOutComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    cancelHandler() {
        this.router.navigateByUrl("/main-login").catch();
    }

    confirmHandler() {
        this.router.navigateByUrl("/").catch();
    }

}
