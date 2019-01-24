import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {UserObs} from "../../services/model/userObs";

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

    constructor() {
    }

    ngOnInit() {
        this.title = this.title || "";
        this.content = this.content || "";
        this.color = this.color || "";

        this.userObservable.subscribe(user => {
            this.title = user.getNick();
            this.content = user.getDescription();
            this.color = user.getPhoto().photo;
        })

    }

}
