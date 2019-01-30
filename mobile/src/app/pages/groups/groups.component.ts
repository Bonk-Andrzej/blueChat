import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {ChannelDtoShort} from '../../repository/channel/channelDtoShort';
import {UserProfileService} from '../../services/user-profile.service';

@Component({
    selector: 'app-groups',
    templateUrl: './groups.component.html',
    styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {

    groups : Observable<Array<ChannelDtoShort>>;

    constructor(private router: Router,
                private userProfileService: UserProfileService) {

        this.groups = this.userProfileService.getChannels();
    }

    ngOnInit() {

    }

    createGroup() {
        this.router.navigateByUrl('/create-group').catch()
    }

    showGroupProfileHandler(group: ChannelDtoShort){
        this.router.navigateByUrl("/group-profile/" + group.idChannel).catch()
    }

}

