import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    // name: string;
    // descreption: string;
    // userProfile = [
    //     }
    // ];
    name: string;
    descreption: string;
    constructor() {
      }
    ngOnInit() {
        this.name = 'Paweł Jastrzębski'
        this.descreption = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error eveniet neque veritatis nihil recusandae, omnis velit, expedita non, dolore maiores tempore debitis consequuntur doloribus pariatur esse incidunt.'

    }

}
