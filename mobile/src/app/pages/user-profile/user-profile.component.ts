import {Component, OnInit} from '@angular/core';
import {UserDto} from '../../repository/user/userDto';
import {UserProfileService} from '../../services/user-profile.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRepositoryService} from "../../repository/user/user-repository.service";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {


    public userDto: UserDto = new UserDto();

    constructor(private router: Router,
                private activeRout: ActivatedRoute,
                private userProfileService: UserProfileService,
                private userRepositoryService: UserRepositoryService
    ) {
    }

    async ngOnInit() {
        console.log('>>>> USER PROFILE SHOW COLOR ' +this.userDto + '<<< NULL ????')
        const paramId = this.activeRout.snapshot.params["id"];
        if(paramId != null){
            this.userDto = await this.userRepositoryService.getUserById(paramId);
        }else {
            this.userDto = this.userProfileService.getUser();
        }

    }

    onGroups() {
        this.router.navigateByUrl('/groups');
    }

    onSearch() {

    }

    onFriends() {
        this.router.navigateByUrl('/friendDtoList');
    }

    onAddFriend() {

    }
}
