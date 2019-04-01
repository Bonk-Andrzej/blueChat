import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {UserProfileService} from "../../services/user-profile.service";
import {Observable} from "rxjs";
import {FriendsObs} from "../../services/model/friendsObs";

@Component({
    selector: 'app-create-group',
    templateUrl: './create-group.component.html',
    styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {

    colorButton: string;
    colorTextOnButton: string;
    image: string;
    public listToCreate: Array<FriendsObs> = new Array<FriendsObs>();

    public form = {
        groupName: "",
        description: ""

    };

    public friends: Observable<Array<FriendsObs>>;

    constructor(private colorService: ColorsService,
                private userProfileService: UserProfileService) {
        this.friends = new Observable<Array<FriendsObs>>();

    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--orange');
        this.colorTextOnButton = this.colorService.getColor('--white-light');
        this.friends = this.userProfileService.getFriends()
        let x = true;
        this.image = `url(assets/checkbox-none.svg)`;
        // setInterval(()=>{
        //
        //     if(x){
        //         this.image = `url(assets/checkbox-none.svg)`;
        //     }else{
        //         this.image = `url(assets/checkbox-accept.svg)`;
        //     }
        //     x = !x;
        //
        // },500)
    }

    addToList(friend: FriendsObs) {
        this.listToCreate.push(friend);
    }

    removeFromList(friendToRemove: FriendsObs) {
        this.listToCreate = this.listToCreate.filter(friend => friend !== friendToRemove)
    }
}
