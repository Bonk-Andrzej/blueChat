import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserProfileService} from '../../services/user-profile.service';
import {Observable} from 'rxjs';
import {UserDtoShort} from '../../repository/user/userDtoShort';
import {FriendsDto} from '../../repository/friend/friendsDto';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit {

    // friends = [
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

    friends : Observable<Array<FriendsDto>>;
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
