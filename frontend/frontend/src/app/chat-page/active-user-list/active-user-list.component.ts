import {Component, OnInit} from '@angular/core';

import {UserRepositoryService} from '../../repository/user-repository/user-repository.service';
import {AuthorizationServiceService} from '../../services/authorization-service/authorization-service.service';
import {MessageWsrService} from '../../services/messege-service/message-wsr.service';
import {UserDTO} from '../../repository/user-repository/user-d-t.o';

@Component({
    selector: 'app-active-user-list',
    templateUrl: './active-user-list.component.html',
    styleUrls: ['./active-user-list.component.scss']
})
export class ActiveUserListComponent implements OnInit {

    public users: Array<UserDTO>;
    public activeUserStatusBar = {
        'backgroundColor': '#df1b37'
    };

    constructor(private userRepository: UserRepositoryService,
                private authorizationService: AuthorizationServiceService,
                private messageService: MessageWsrService) {

        this.users = [];
    }

    ngOnInit() {

        this.userRepository.getUsers().subscribe(users => {
            console.log(users);
            for (const user of users) {
                this.users.push(new UserDTO(user.idUser, user.nick));
            }
        });
        document.documentElement.style.setProperty('--status-color', '#df1b37');
    }

    setReceiver(user: UserDTO) {
        this.messageService.setReceiver(user);
        this.activeUserStatusBar.backgroundColor = '#56c130';
        document.documentElement.style.setProperty('--status-color', '#56c130');
    }

}