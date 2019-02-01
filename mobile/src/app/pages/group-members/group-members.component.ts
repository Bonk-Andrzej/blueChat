import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {Router} from "@angular/router";
import {GroupProfileService} from "../../services/group-profile.service";
import {UserShortObs} from "../../services/model/userShortObs";

@Component({
    selector: 'app-group-members',
    templateUrl: './group-members.component.html',
    styleUrls: ['./group-members.component.scss']
})
export class GroupMembersComponent implements OnInit {



    members: BehaviorSubject<Array<UserShortObs>>;

    constructor(private router: Router,
                private groupProfileService: GroupProfileService) {

        this.members = new BehaviorSubject<Array<UserShortObs>>([]);
    }

    ngOnInit() {
        this.groupProfileService.getCurrnetGroup().subscribe(group => {
            this.members.next(group.getMembersList())
        })
    }

    public getMembers(): Observable<Array<UserShortObs>>{
        return this.members.asObservable()
    }


    public showProfile(idUser: number) {
        this.router.navigateByUrl("/user-profile/" + idUser).catch();
    }
}
