import {Component, OnInit} from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
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
    createGroup(){
        this.router.navigateByUrl('/create-group')
    }
    mainLogin(){
        this.router.navigateByUrl('/main-login')
    }

    setting() {
        alert('We are working at this ..')
        // this.router.navigateByUrl()
    }

    logOut() {
        this.router.navigateByUrl('/log-out')
    }
}
