import {Component, OnInit} from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';

import {animate, state, style, transition, trigger} from '@angular/animations';
import {ColorsService} from '../../services/colors.service';
import {Router} from '@angular/router';
import {UserProfileService} from '../../services/user-profile.service';
import {FriendsDto} from '../../repository/friend/friendsDto';
import {Observable} from 'rxjs';
import {ChannelDtoShort} from '../../repository/channel/channelDtoShort';

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
    groups = [
        {
            'name': 'Java Poz 9',
            'noReadMessages': '5'
        },
        {
            'name': 'random',
            'noReadMessages': '3'
        },
        {
            'name': 'off topic',
            'noReadMessages': '1'
        }
    ];

    users = [
        {
            'nick': 'Paweł Jastrzębski',
            'noReadMessages': '2'
        },
        {
            'nick': 'Igor Sowiński',
            'noReadMessages': '11'
        },
        {
            'nick': 'Marian Kowalski',
            'noReadMessages': '1'
        }
    ];


    titleStyle = {
        paddingLeft: '10px'
    };
    listStyle = {
        padding: '5px',
        gridGap: '3px'
    };
    backgroundAnimationStatus = 'hide';
    backgroundColorList: string;

    friends: Observable<Array<FriendsDto>>;
    channels: Observable<Array<ChannelDtoShort>>;

    constructor(public leftMenuService: LeftMenuServiceService,
                private colorService: ColorsService,
                private router: Router,
                private userProfileService: UserProfileService) {
    }

    ngOnInit() {
        this.backgroundAnimationStatus = 'hide';
        this.leftMenuService.onToggle(this.onToggleHandler.bind(this));
        this.backgroundColorList = this.colorService.getColor('--black');
        this.friends = this.userProfileService.getFriends();
        this.channels = this.userProfileService.getChannels();
    }

    private onToggleHandler(isDisplay: boolean) {

        if (isDisplay) {
            this.backgroundAnimationStatus = 'show';
        } else {
            this.backgroundAnimationStatus = 'hide';
        }

    }

    showConversation() {
        this.leftMenuService.toggle();
        this.router.navigateByUrl('/conversation');
    }


}
