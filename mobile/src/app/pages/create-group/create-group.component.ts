import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {UserProfileService} from '../../services/user-profile.service';
import {Observable} from 'rxjs';
import {FriendsObs} from '../../services/model/friendsObs';

@Component({
    selector: 'app-create-group',
    templateUrl: './create-group.component.html',
    styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {

    colorButton: string;
    colorTextOnButton: string;
    // image: string;
    confirmStatus: string;
    public listToCreate: Array<FriendsObs> = new Array<FriendsObs>();

    public form = {
        groupName: '',
        description: '',
        public: true,
        userListId: []
    };

    public friends: Observable<Array<FriendsObs>>;

    constructor(private colorService: ColorsService,
                private userProfileService: UserProfileService) {
        this.friends = new Observable<Array<FriendsObs>>();

    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--orange');
        this.colorTextOnButton = this.colorService.getColor('--white-light');
        this.friends = this.userProfileService.getFriends();
        this.confirmStatus = 'none';
        // this.image = `url(assets/checkbox-none.svg)`;
    }

    //settings current list
    addToList(friend: FriendsObs): void {
        this.listToCreate.push(friend);
    }

    removeFromList(friendToRemove: FriendsObs): void {
        this.listToCreate = this.listToCreate.filter(friend => friend !== friendToRemove);
    }

    //settings status public / private
    changeRange(isPublic: boolean): void {
        this.form.public = isPublic;
    }

    // settings accepting create group
    createGroup(): void {
        console.log('LISTA FRIENDSOW' + this.listToCreate);
        console.log('LISTA userow z formularza' + this.form.userListId);


        if (this.validateFields() != false) {
            this.addSelectedUsers(this.listToCreate);
            this.confirmStatus = 'block';
        } else {
            alert('Brakuje czegos');
        }
    }

    //setting confirm status
    onCancel(): void {
        this.confirmStatus = 'none';

    }

    onConfirmEdit(): void {
        console.log('UDALO CI SIE ');
        this.clearForm();
    }


    private validateFields(): boolean {
        if ((this.form.groupName == '') || (this.form.description == '') || (this.listToCreate.length == 0)) {
            return false;
        } else {
            return true;
        }
    }

    private addSelectedUsers(friendsList: Array<FriendsObs>) {
        friendsList.forEach(friend => {
            this.form.userListId.push(friend.getFriend().getIdUser());
        });
    }

    private clearForm(): void {
        this.form.groupName = '';
        this.form.description = '';
        this.changeRange(true);
        this.form.userListId = [];
        this.listToCreate = new Array<FriendsObs>();
    }

}
