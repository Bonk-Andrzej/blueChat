import {EventEmitter, Injectable} from '@angular/core';
import {WSRClientService} from '../WSRClient/wsrclient.service';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {Router} from '@angular/router';
import {UserDto} from '../repository/user/userDto';

@Injectable({
  providedIn: 'root'
})
export class RetrieveStateApplicationService {

  constructor(private userRepository: UserRepositoryService,
              private webSocketService : WSRClientService,
              private router: Router) {

    console.log("start: RetrieveStateApplicationService")
    this.retrieveStatusApplication().catch();
  }

    private async retrieveStatusApplication() {

        const id: string = localStorage.getItem('userId');
        if (id != null) {
            const user = await this.userRepository.getUserById(parseInt(id));
            this.onRetrieveApplicationState.emit(user);
            this.router.navigateByUrl('/main-login').catch();
        } else {
            this.router.navigateByUrl('/').catch();
        }
    }

    public saveUserId(user : UserDto){
        // localStorage.setItem('userId', user.idUser + '');
    }

    public removeUserId(){
        localStorage.removeItem("userId");
    }

    public onRetrieveApplicationState = new EventEmitter<UserDto>();
}
