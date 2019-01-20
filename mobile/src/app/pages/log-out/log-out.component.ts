import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { Location } from '@angular/common';
import {RetrieveStateApplicationService} from '../../services/retrieve-state-application.service';

@Component({
    selector: 'app-log-out',
    templateUrl: './log-out.component.html',
    styleUrls: ['./log-out.component.scss']
})
export class LogOutComponent implements OnInit {

    constructor(private router: Router,
                private location: Location,
                private retrieveStateApplicationService: RetrieveStateApplicationService ) {
    }

    ngOnInit() {
    }

    cancelHandler() {
        this.location.back();
    }

    confirmHandler() {
        this.retrieveStateApplicationService.removeUserId();
        this.router.navigateByUrl("/").catch();
    }

}
