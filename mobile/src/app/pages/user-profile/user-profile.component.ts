import {Component, OnInit} from '@angular/core';
import {UserDto} from '../../repository/user/userDto';
import {UserProfileService} from '../../services/user-profile.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRepositoryService} from "../../repository/user/user-repository.service";
import {BehaviorSubject, Observable} from "rxjs";
import {UserObs} from "../../services/model/userObs";
import {UserShortObs} from '../../services/model/userShortObs';
import {InvitationService} from '../../services/invitation.service';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    private pointedUser: UserShortObs;
    private confirmFunction;
    private userBeh: BehaviorSubject<UserObs> = new BehaviorSubject<UserObs>(new UserObs());
    public firstButtonProperties = {
        iconName: "",
        name: ""
    };
    public titleConfirmBox;
    public confirmStatus;
    constructor(private router: Router,
                private activeRout: ActivatedRoute,
                private userProfileService: UserProfileService,
                private userRepositoryService: UserRepositoryService,
                private invitationService: InvitationService
    ) {
}

    async ngOnInit() {
        this.setAddButtonIcon();
        this.confirmStatus = 'none';
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
            console.log('USER >>>>>>>>>>>>>' , user);
            console.log('ID USER >>>>>>>>>>>>>' , paramId);
            if(user != null){
                this.pointedUser = user;
                this.firstButtonProperties.iconName = "remove.svg";
                this.firstButtonProperties.name = "Delete";
                this.titleConfirmBox = 'Do you want delete friend ?';
                this.confirmFunction = ()=>{
                    console.log('FRIEND >>>>>>');
                    this.toggleShowConfirm();
                };
            }else {
                let userToAdd: UserShortObs = new UserShortObs();
                userToAdd.setIdUser(paramId);
                this.pointedUser = userToAdd;
                this.firstButtonProperties.iconName = "add-person-icon.svg";
                this.firstButtonProperties.name = "Add";
                this.titleConfirmBox = 'Do you want add to friend ?';
                this.confirmFunction = ()=>{
                    console.log('ADDD >>>>>>');
                    this.toggleShowConfirm();
                };
            }
        }else {
            this.firstButtonProperties.iconName = "edit.svg";
            this.firstButtonProperties.name = "Edit";
            this.titleConfirmBox = 'Do you want edit your profile?';
            this.confirmFunction = ()=>{
                console.log('EDIT!!! >>>>>>');
                this.toggleShowConfirm();
            };
        }

    }

    public getUserObs(): Observable<UserObs>{
        return this.userBeh.asObservable();
    }

    private toggleShowConfirm(){
        if(this.confirmStatus === 'none'){
            this.confirmStatus = 'block'
        }else {
            this.confirmStatus = 'none'
        }
    }
    private onCancel(){
        this.toggleShowConfirm();
    }
    private onConfirm(){
        if(this.firstButtonProperties.name == 'Delete'){
            console.log('You edit you delete' , this.pointedUser.getNick());
            let idFriendship : number = this.userProfileService.getIdFriendship(this.pointedUser.getIdUser());

            this.invitationService.removeFriendship(idFriendship);
            this.userProfileService.refreshFriendsList();
            this.toggleShowConfirm();

        }
        if(this.firstButtonProperties.name == 'Add'){
            let sender = this.userProfileService.getUser();
            this.invitationService.sendInvitation(sender.getIdUser(), this.pointedUser.getIdUser());
            this.userProfileService.refreshFriendsList();
            this.toggleShowConfirm();
        }
        if(this.firstButtonProperties.name == 'Edit'){
            console.log('You edit you profile')
        }

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

        this.confirmFunction()
    }
}
