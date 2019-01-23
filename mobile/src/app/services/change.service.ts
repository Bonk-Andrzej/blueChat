import {EventEmitter, Injectable} from '@angular/core';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {LocalType} from '../WSRClient/types/LocalType';
import {UserDto} from '../repository/user/userDto';

@Injectable({
    providedIn: 'root'
})
export class ChangeService {

    constructor(private wsrClientService: WSRClientService) {


        wsrClientService.WRSClient.addProcedure(LocalType.FRIENDJOIN, new UserDto(), (user) => {
            console.log("user logged: " + user.nick);
            this.onFriendJoin.emit(user);
        })

        wsrClientService.WRSClient.addProcedure(LocalType.FRIENDLEAVE, new UserDto(), (user) => {
            console.log("user logged out: " + user.nick);
            this.onFriendLeave.emit(user);
        })

    }

    public onFriendJoin = new EventEmitter<UserDto>();
    public onFriendLeave = new EventEmitter<UserDto>();

}
