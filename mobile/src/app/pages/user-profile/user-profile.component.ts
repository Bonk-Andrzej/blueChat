import {Component, OnInit} from '@angular/core';
import {UserProfileService} from '../../services/user-profile.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRepositoryService} from '../../repository/user/user-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {UserObs} from '../../services/model/userObs';
import {UserShortObs} from '../../services/model/userShortObs';
import {InvitationService} from '../../services/invitation.service';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    private pointedUser: UserShortObs;
    public confirmStatus;

    private userBeh: BehaviorSubject<UserObs> = new BehaviorSubject<UserObs>(new UserObs());
    public firstButtonState: StatesManager;

    constructor(private router: Router,
                private activeRout: ActivatedRoute,
                private userProfileService: UserProfileService,
                private userRepositoryService: UserRepositoryService,
                private invitationService: InvitationService
    ) {

        this.firstButtonState = new StatesManager();
        this.firstButtonState.addState('ADD',
            'add-person-icon.svg',
            'Add',
            'Do you want add to friend ?',
            () => {
                console.log('ADDD >>>>>>');
                this.toggleShowConfirm();
            },
            () => {
                let sender = this.userProfileService.getUser();
                this.invitationService.sendInvitation(sender.getIdUser(), this.pointedUser.getIdUser());
                this.toggleShowConfirm();
            });

        this.firstButtonState.addState('DELETE',
            'remove.svg',
            'Delete',
            'Do you want delete friend ?',
            () => {
                this.toggleShowConfirm();
            }, () => {
                let idFriendship: number = this.userProfileService.getIdFriendship(this.userBeh.getValue().getIdUser());
                this.invitationService.removeFriendship(idFriendship);
                this.userProfileService.removeFriendFromList(this.userBeh.getValue().getIdUser());
                this.toggleShowConfirm();
            });

        this.firstButtonState.addState('EDIT',
            'edit.svg',
            'Edit',
            'Do you want edit your profile?',
            () => {
                this.router.navigateByUrl('/edit-profile');
            },() => {
                this.router.navigateByUrl('/edit-profile');
            });


    }

    async ngOnInit() {
        this.setButtonStatus();
        this.confirmStatus = 'none';
        const paramId = this.activeRout.snapshot.params['id'];
        if (paramId != null) {
            const userDto = await this.userRepositoryService.getUserById(paramId);
            this.userBeh.next(UserObs.create(userDto));
        } else {
            this.userBeh.next(this.userProfileService.getUser());
        }
    }

    private setButtonStatus() {
        const paramId = this.activeRout.snapshot.params['id'];
        if (paramId != null) {
            let user = this.userProfileService.findFreind(paramId);
            console.log('USER >>>>>>>>>>>>>', user);
            console.log('ID USER >>>>>>>>>>>>>', paramId);
            if (user != null) {
                this.firstButtonState.setStateName('DELETE');
            } else {
                let userToAdd: UserShortObs = new UserShortObs();
                userToAdd.setIdUser(paramId);
                this.pointedUser = userToAdd;
                this.firstButtonState.setStateName('ADD');
            }
        } else {
            this.firstButtonState.setStateName('EDIT');
        }

    }

    public getUserObs(): Observable<UserObs> {
        return this.userBeh.asObservable();
    }

    private toggleShowConfirm() {
        if (this.confirmStatus === 'none') {
            this.confirmStatus = 'block';
        } else {
            this.confirmStatus = 'none';
        }
    }

    private onCancel() {
        this.toggleShowConfirm();
    }

    private onConfirm() {
        this.firstButtonState.getState().confirmFunction();
    }


    // Click events
    showGroups() {
        this.router.navigateByUrl('/groups');
    }

    onSearch() {
        this.router.navigateByUrl('/search');

    }

    showFriends() {
        this.router.navigateByUrl('/friendDtoList');
    }

    onAddFriend() {
        this.firstButtonState.getState().clickFunction();
    }
}


class ButtonState {

    iconName: string;
    name: string;
    titleConfirmBox: string;
    confirmFunction: () => void;
    clickFunction: () => void;


    constructor(iconName = '', name = '', titleConfirmBox = '', onClickFunction = () => {}, confirmFunction = () => {}) {
        this.iconName = iconName;
        this.name = name;
        this.titleConfirmBox = titleConfirmBox;
        this.confirmFunction = confirmFunction;
        this.clickFunction = onClickFunction;
    }
}
class StatesManager {

    private states: Array<ButtonState> = [];
    private selectedState = 'DEFAULT';
    private readonly defaultStateName = 'DEFAULT';

    constructor() {
        this.addState('DEFAULT');
    }

    public addState(stateName: string, iconName = '', name = '', titleConfirmBox = '', onClickFunction = () => {
    }, confirmFunction = () => {
    }) {
        this.states[stateName] = (new ButtonState(iconName, name, titleConfirmBox, onClickFunction, confirmFunction));
    }

    getState(stateName = this.selectedState): ButtonState {
        return (this.states[stateName] != null) ? this.states[stateName] : this.getState(this.defaultStateName);
    }

    setStateName(stateName: string) {
        this.selectedState = stateName;
    }
}
