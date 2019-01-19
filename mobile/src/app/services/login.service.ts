import {EventEmitter, Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {UserDto} from '../repository/user/userDto';
import {UserPassDto} from '../repository/user/userPassDto';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private userRepository: UserRepositoryService) {
    }

    public async Login(userPassDto: UserPassDto) {


        console.log(userPassDto);

        let user = await this.userRepository.logInUser(userPassDto).catch(reason => {
            throw(reason);
        });
        this.onLogin.emit(user);
        console.log(user);


    }

    // Events
    public onLogin = new EventEmitter<UserDto>();
}
