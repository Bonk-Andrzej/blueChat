import {Component, OnInit} from '@angular/core';
import {UserProfileService} from '../../services/user-profile.service';
import {UserObs} from '../../services/model/userObs';
import {Observable} from 'rxjs';

@Component({
    selector: '[app-edit-profile]',
    templateUrl: './edit-profile.component.html',
    styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

    public userObse: Observable<UserObs>;

    constructor(private userProfileService: UserProfileService) {
    }

    ngOnInit() {
        this.userObse = this.userProfileService.getUserObs();
        console.log('>>>>>>>>>>>>>>>>>>>>>>>> USER W PROFILE', this.userObse)

    }

    // getUserObs() : UserObs {
    // return this.userObse;
    // }
}
