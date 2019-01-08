import {Component, OnInit} from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';

import {animate, keyframes, state, style, transition, trigger} from '@angular/animations';

@Component({
    selector: 'app-left-menu',
    templateUrl: './left-menu.component.html',
    styleUrls: ['./left-menu.component.scss'],
    animations: [
        trigger('hide-bg', [
            state('hide', style({opacity: 0, display: 'none'})),
            state('show', style({opacity: 0.5, display: 'block'})),
            transition('* => *',
                animate('200ms'))
        ]),
        trigger('hide-menu', [
            state('hide', style({left: -300, display: 'none'})),
            state('show', style({left: 0, display: 'block'})),
            transition('* => *',
                animate('200ms'))
        ]),
    ],
})
export class LeftMenuComponent implements OnInit {

    backgroundAnimationStatus = 'hide';


    constructor(public leftMenuService: LeftMenuServiceService) {
    }

    ngOnInit() {
        this.backgroundAnimationStatus = 'hide';
        this.leftMenuService.onToggle(this.onToggleHandler.bind(this));
    }

    private onToggleHandler(isDisplay: boolean) {

        if (isDisplay) {
            this.backgroundAnimationStatus = 'show';
        } else {
            this.backgroundAnimationStatus = 'hide';
        }

    }

}
