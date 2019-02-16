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
                this.invitationService.sendInvitation(sender.getIdUser(), this.userBeh.getValue().getIdUser());
                this.toggleShowConfirm();
                this.firstButtonState.setState("ADDDISABLE")
            });

        this.firstButtonState.addState('ADDDISABLE',
            'add-person-icon.svg',
            'Add',
            'Do you want add to friend ?');


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
                this.firstButtonState.setState("ADD")
            });

        this.firstButtonState.addState('EDIT',
            'edit.svg',
            'Edit',
            'Do you want edit your profile?',
            () => {
                this.router.navigateByUrl('/edit-profile');
            }, () => {
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
            let friend = this.userProfileService.findFreind(paramId);
            if (friend != null) {
                this.firstButtonState.setState('DELETE');
            } else {
                this.firstButtonState.setState('ADD');
            }
        } else {
            this.firstButtonState.setState('EDIT');
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

    onCancel() {
        this.toggleShowConfirm();
    }

    onConfirm() {
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


export class ButtonState {

    iconName: string;
    name: string;
    titleConfirmBox: string;
    confirmFunction: () => void;
    clickFunction: () => void;

    constructor(iconName = '', name = '', titleConfirmBox = '', onClickFunction = () => {
    }, confirmFunction = () => {
    }) {
        this.iconName = iconName;
        this.name = name;
        this.titleConfirmBox = titleConfirmBox;
        this.confirmFunction = confirmFunction;
        this.clickFunction = onClickFunction;
    }
}

class StatesManager {

    private states: Array<ButtonState> = [];
    private readonly defaultStateName = 'DEFAULT';
    private selectedState = this.defaultStateName;
    private stateBech: BehaviorSubject<ButtonState>;

    constructor() {
        this.addState(this.defaultStateName);
        this.stateBech = new BehaviorSubject<ButtonState>(this.getState());
    }

    public addState(stateName: string, iconName = '', name = '', titleConfirmBox = '', onClickFunction = () => {
    }, confirmFunction = () => {
    }) {
        this.states[stateName] = (new ButtonState(iconName, name, titleConfirmBox, onClickFunction, confirmFunction));
    }

    getState(stateName = this.selectedState): ButtonState {
        return (this.states[stateName] != null) ? this.states[stateName] : this.getState(this.defaultStateName);
    }

    getStateObs(): Observable<ButtonState> {
        return this.stateBech.asObservable();
    }

    setState(stateName: string) {
        this.selectedState = stateName;
        this.stateBech.next(this.getState())
    }
}
