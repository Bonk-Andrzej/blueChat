import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ChannelRepositoryService} from "../../repository/channel/channel-repository.service";
import {ChannelObs} from "../../services/model/channelObs";

@Component({
    selector: 'app-group-profile',
    templateUrl: './group-profile.component.html',
    styleUrls: ['./group-profile.component.scss']
})
export class GroupProfileComponent implements OnInit {


    private groupBehavior: BehaviorSubject<ChannelObs>;
    public joinButtonStyle = {
        backgroundImage: ""
    };

    constructor(private router: Router,
                private activeRout: ActivatedRoute,
                private channelRepositoryService: ChannelRepositoryService
    ) {
        this.groupBehavior = new BehaviorSubject<ChannelObs>(new ChannelObs());
    }

    async ngOnInit() {
        const groupId = this.activeRout.snapshot.params["id"];
        const channelDto = await this.channelRepositoryService.getById(groupId);
        this.groupBehavior.next(ChannelObs.create(channelDto));
    }

    public joinButtonHandler() {

    }

    public membersButtonHandler() {

    }

    public getGroupObs(): Observable<ChannelObs> {
        return this.groupBehavior.asObservable();
    }


}
