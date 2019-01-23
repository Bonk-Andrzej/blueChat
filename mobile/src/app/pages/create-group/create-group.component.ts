import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {UserProfileService} from "../../services/user-profile.service";
import {Observable} from "rxjs";
import {FriendsObs} from "../../services/model/friendsObs";

@Component({
    selector: 'app-create-group',
    templateUrl: './create-group.component.html',
    styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {

    colorButton: string;
    colorTextOnButton: string;
    public form = {
        groupName: "",
        description: ""

    };

    public friends: Observable<Array<FriendsObs>>;

    constructor(private colorService: ColorsService,
                private userProfileService: UserProfileService) {
        this.friends = new Observable<Array<FriendsObs>>();

    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--orange');
        this.colorTextOnButton = this.colorService.getColor('--white-light');
        this.friends = this.userProfileService.getFriends()

    }

}
