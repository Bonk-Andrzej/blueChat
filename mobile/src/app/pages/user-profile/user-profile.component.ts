import {Component, OnInit} from '@angular/core';
import {UserDto} from '../../repository/user/userDto';
import {UserProfileService} from '../../services/user-profile.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRepositoryService} from "../../repository/user/user-repository.service";
import {BehaviorSubject, Observable} from "rxjs";
import {UserObs} from "../../services/model/userObs";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {


    private userBeh: BehaviorSubject<UserObs> = new BehaviorSubject<UserObs>(new UserObs());
    public addFriendButtonStyle = {
        backgroundImage: ""
    };

    constructor(private router: Router,
                private activeRout: ActivatedRoute,
                private userProfileService: UserProfileService,
                private userRepositoryService: UserRepositoryService
    ) {
}

    async ngOnInit() {
        this.setAddButtonIcon();
        const paramId = this.activeRout.snapshot.params["id"];
        if(paramId != null){
            const userDto = await this.userRepositoryService.getUserById(paramId)
            this.userBeh.next(UserObs.create(userDto));
        }else {
            this.userBeh.next(this.userProfileService.getUser());
        }
    }

    private setAddButtonIcon(){
        const paramId = this.activeRout.snapshot.params["id"];
        if(paramId != null){
            let user = this.userProfileService.findFreind(paramId);
            if(user != null){
                this.addFriendButtonStyle.backgroundImage = "url(/assets/remove.svg)";
            }else {
                this.addFriendButtonStyle.backgroundImage = "url(/assets/add-person-icon.svg)";
            }
        }else {
            this.addFriendButtonStyle.backgroundImage = "url(/assets/edit.svg)";
        }

    }

    public getUserObs(): Observable<UserObs>{
        return this.userBeh.asObservable();
    }


    // Click events
    onGroups() {
        this.router.navigateByUrl('/groups');
    }

    onSearch() {
        this.router.navigateByUrl('/search');

    }

    onFriends() {
        this.router.navigateByUrl('/friendDtoList');
    }

    onAddFriend() {

    }
}
