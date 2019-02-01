import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ChannelRepositoryService} from "../../repository/channel/channel-repository.service";
import {ChannelObs} from "../../services/model/channelObs";
import {GroupProfileService} from "../../services/group-profile.service";

@Component({
    selector: 'app-group-profile',
    templateUrl: './group-profile.component.html',
    styleUrls: ['./group-profile.component.scss']
})
export class GroupProfileComponent implements OnInit {

    private groupObs: Observable<ChannelObs>;

    constructor(private router: Router,
                private activeRout: ActivatedRoute,
                private groupProfile: GroupProfileService
    ) {
        this.groupObs = new BehaviorSubject<ChannelObs>(new ChannelObs());
    }

    async ngOnInit() {
        const groupId = this.activeRout.snapshot.params["id"];
        this.groupObs = this.groupProfile.getGroup(groupId);
    }

    public joinButtonHandler() {

    }

    public membersButtonHandler() {
        this.router.navigateByUrl("/group-members").catch()
    }

    public getGroupObs(): Observable<ChannelObs> {
        return this.groupObs;
    }


}
