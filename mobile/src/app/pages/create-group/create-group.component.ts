import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {UserProfileService} from '../../services/user-profile.service';
import {Observable} from 'rxjs';
import {FriendsObs} from '../../services/model/friendsObs';
import {ChannelDtoCreate} from '../../repository/channel/channelDtoCreate';
import {PhotoDto} from '../../repository/photo/photoDto';
import {GroupProfileService} from '../../services/group-profile.service';

@Component({
    selector: 'app-create-group',
    templateUrl: './create-group.component.html',
    styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {

    colorButton: string;
    colorTextOnButton: string;
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
                private userProfileService: UserProfileService,
                private groupProfileService: GroupProfileService) {
        this.friends = new Observable<Array<FriendsObs>>();

    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--orange');
        this.colorTextOnButton = this.colorService.getColor('--white-light');
        this.friends = this.userProfileService.getFriends();

        this.confirmStatus = 'none';
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
    //TODO REFACTOR RESPONSIBILITY TO SERVICE
    onConfirmEdit(): void {
        let channelToCreate: ChannelDtoCreate = new ChannelDtoCreate();
        channelToCreate.name = this.form.groupName;
        channelToCreate.publicChannel = this.form.public;
        console.log('hanał jaki ???? ' + this.form.public);
        channelToCreate.photoDto = this.setDefaultPhoto();
        channelToCreate.userIdChannelOwner = this.userProfileService.getUser().getIdUser();
        channelToCreate.userList = this.form.userListId;
        //added himself to the channel user
        channelToCreate.userList.push(this.userProfileService.getUser().getIdUser());
        this.groupProfileService.createChannel(channelToCreate);


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
        this.confirmStatus = 'none';
    }

    private setDefaultPhoto(): PhotoDto {
        if (this.form.public == true) {
            let publicPhoto = new PhotoDto();
            publicPhoto.idPhoto = 7;
            publicPhoto.photo = 'rgb(33,27,29)';
            return publicPhoto;
        } else {
            let privatePhoto = new PhotoDto();
            privatePhoto.idPhoto = 6;
            privatePhoto.photo = 'rgb(23,151,11)';
            return privatePhoto
        }
    }

}
