import {Component, OnInit} from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';
import {Router} from '@angular/router';

@Component({
    selector: '[app-menu-bottom]',
    templateUrl: './menu-bottom.component.html',
    styleUrls: ['./menu-bottom.component.scss'],
})
export class MenuBottomComponent implements OnInit {

    constructor(public leftMenuService: LeftMenuServiceService,
                private router: Router) {
    }

    ngOnInit() {
    }

    userProfile() {
        this.router.navigateByUrl('/user-profile');
    }

    mainLogin() {
        this.router.navigateByUrl('/main-login');
    }

    setting() {
        this.router.navigateByUrl('/options');
    }

    logOut() {
        this.router.navigateByUrl('/log-out');
    }
}
