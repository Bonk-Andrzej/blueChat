import {EventEmitter, Injectable} from '@angular/core';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {LocalType} from '../WSRClient/types/LocalType';
import {UserDto} from '../repository/user/userDto';

@Injectable({
  providedIn: 'root'
})
export class ChangeService {

  constructor(private wsrClientService : WSRClientService) {


    wsrClientService.WRSClient.addProcedure(LocalType.NEWACTIVEFRIEND, new UserDto(),(user)=>{
      console.log("user logged: "+ user.nick);
      this.onNewAcitveFriend.emit(user);
    })

  }
    public onNewAcitveFriend = new EventEmitter<UserDto>();

}
