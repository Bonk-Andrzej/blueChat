import {Component, OnInit} from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';

import {animate, state, style, transition, trigger} from '@angular/animations';
import {ColorsService} from '../../services/colors.service';

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
    backgroundColorList: string;

    constructor(public leftMenuService: LeftMenuServiceService,
                private colorService: ColorsService) {
    }

    ngOnInit() {
        this.backgroundAnimationStatus = 'hide';
        this.leftMenuService.onToggle(this.onToggleHandler.bind(this));
        // this.backgroundColorList = this.colorService.getColor('--black');
        this.backgroundColorList = this.colorService.getColor('--white');
    }

    private onToggleHandler(isDisplay: boolean) {

        if (isDisplay) {
            this.backgroundAnimationStatus = 'show';
        } else {
            this.backgroundAnimationStatus = 'hide';
        }

    }

}
