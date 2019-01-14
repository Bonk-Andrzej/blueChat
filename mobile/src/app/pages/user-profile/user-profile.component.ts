import {Component, OnInit} from '@angular/core';
import {UserDto} from '../../repository/user/userDto';
import {UserProfileService} from '../../services/user-profile.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    descreption: string;

    public userDto: UserDto = new UserDto();

    constructor(private router: Router,
                private userProfileService: UserProfileService,
    ) {
    }

    ngOnInit() {
        this.userDto.name = 'Paweł Jastrzębski';
        this.userDto.description = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error eveniet neque veritatis nihil recusandae, omnis velit, expedita non, dolore maiores tempore debitis consequuntur doloribus pariatur esse incidunt.';
        this.userDto = this.userProfileService.getUser();
        console.log(this.userProfileService);
    }

    onGroups() {
        this.router.navigateByUrl('/groups');
    }

    onSearch() {

    }

    onFriends() {

    }

    onAddFriend() {

    }
}
