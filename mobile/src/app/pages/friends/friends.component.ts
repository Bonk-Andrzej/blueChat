import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserProfileService} from '../../services/user-profile.service';
import {Observable} from 'rxjs';
import {FriendsDto} from '../../repository/friend/friendsDto'
import {FriendsObs} from "../../services/model/friendsObs";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit {


    friends : Observable<Array<FriendsObs>>;
    ngOnInit() {
        this.friends = this.userProfileService.getFriends();

    }

    constructor(private router: Router,
                private userProfileService: UserProfileService) {


    }

    createGroup() {
        this.router.navigateByUrl('/create-group');
    }
}
