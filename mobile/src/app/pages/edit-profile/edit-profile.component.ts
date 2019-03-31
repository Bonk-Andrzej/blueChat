import {Component, OnInit} from '@angular/core';
import {UserProfileService} from '../../services/user-profile.service';
import {UserObs} from '../../services/model/userObs';
import {Observable} from 'rxjs';
import {UserDto} from '../../repository/user/userDto';
import {ColorsService} from '../../services/colors.service';
import {PhotoDto} from '../../repository/photo/photoDto';

@Component({
    selector: 'app-edit-profile',
    templateUrl: './edit-profile.component.html',
    styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {

    public userObse: Observable<UserObs>;
    public editedUser: UserDto;
    public rollButtonColor: string = '';
    public colorTextOnRollButton = 'white';
    public colorTextOnConfirmButton = 'white';
    public confirmColorButton = 'red';

    private currentPhoto: string = '';

    constructor(private userProfileService: UserProfileService,
                private colorService: ColorsService) {
    }

    ngOnInit() {
        this.userObse = this.userProfileService.getUserObs();

        this.userObse.subscribe(user => {
                this.rollButtonColor = user.getPhoto().photo;
            }
        );

        this.editedUser = new UserDto(null, '', '', '', null);

    }

    rollCollor() {
        let randColor = this.colorService.getRandomColor();
        this.rollButtonColor = randColor;
        let photo: PhotoDto = new PhotoDto();
        photo.photo = randColor;
        this.editedUser.photoDto = photo;
    }

    confirmEdit() {

    }
}
