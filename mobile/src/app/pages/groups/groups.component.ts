import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {FriendsDto} from '../../repository/friend/friendsDto';
import {ChannelDtoShort} from '../../repository/channel/channelDtoShort';
import {UserProfileComponent} from '../user-profile/user-profile.component';
import {UserProfileService} from '../../services/user-profile.service';

@Component({
    selector: 'app-groups',
    templateUrl: './groups.component.html',
    styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {

    // groups = [
    //     {
    //         'name': 'Java Poz 9',
    //     },
    //     {
    //         'name': 'random',
    //     },
    //     {
    //         'name': 'off topic',
    //     }
    // ];

    groups : Observable<Array<ChannelDtoShort>>;

    constructor(private router: Router,
                private userProfileService: UserProfileService) {

        this.groups = this.userProfileService.getChannels();
    }

    ngOnInit() {

    }

    createGroup() {
        this.router.navigateByUrl('/create-group');
    }
}

