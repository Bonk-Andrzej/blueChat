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
    public firstButtonProperties = {
        iconName: "",
        name: ""
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
                this.firstButtonProperties.iconName = "remove.svg";
                this.firstButtonProperties.name = "Delete";
            }else {
                this.firstButtonProperties.iconName = "add-person-icon.svg";
                this.firstButtonProperties.name = "Add";
            }
        }else {
            this.firstButtonProperties.iconName = "edit.svg";
            this.firstButtonProperties.name = "Edit";
        }

    }

    public getUserObs(): Observable<UserObs>{
        return this.userBeh.asObservable();
    }


    // Click events
    membersButtonHandler() {
        this.router.navigateByUrl('/groups');
    }

    onSearch() {
        this.router.navigateByUrl('/search');

    }

    joinButtonHandler() {
        this.router.navigateByUrl('/friendDtoList');
    }

    onAddFriend() {

    }
}
