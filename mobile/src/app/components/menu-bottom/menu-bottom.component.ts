import {Component, OnInit} from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
    selector: '[app-menu-bottom]',
    templateUrl: './menu-bottom.component.html',
    styleUrls: ['./menu-bottom.component.scss'],
})
export class MenuBottomComponent implements OnInit {

    constructor(public leftMenuService: LeftMenuServiceService) {
    }

    ngOnInit() {
    }

}
