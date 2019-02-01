import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {UserObs} from "../../services/model/userObs";
import {ChannelObs} from "../../services/model/channelObs";

@Component({
    selector: '[app-box-profiles]',
    templateUrl: './box-profiles.component.html',
    styleUrls: ['./box-profiles.component.scss']
})
export class BoxProfilesComponent implements OnInit {

    @Input() title: string;
    @Input() content: string;
    @Input() color: string;
    @Input() userObservable: Observable<UserObs>;
    @Input() groupObservable: Observable<ChannelObs>;

    constructor() {
    }

    ngOnInit() {
        this.title = this.title || "";
        this.content = this.content || "";
        this.color = this.color || "";
        this.userObservable = this.userObservable || new Observable<UserObs>();
        this.groupObservable = this.groupObservable || new Observable<ChannelObs>();

        this.userObservable.subscribe(user => {
            this.title = user.getNick();
            this.content = user.getDescription();
            this.color = user.getPhoto().photo;
        });

        this.groupObservable.subscribe( group => {
            if(group != null){
                this.title = group.getName();
                this.content = "Group description";
                this.color = group.getPhoto().photo;
            }
        });

    }

}
