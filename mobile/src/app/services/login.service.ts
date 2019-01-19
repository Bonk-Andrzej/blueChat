import {EventEmitter, Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {UserDto} from '../repository/user/userDto';
import {UserPassDto} from '../repository/user/userPassDto';
@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor( private router: Router,
                 private userRepository: UserRepositoryService) {
    }

    public async Login(userPassDto: UserPassDto) {
        console.log(userPassDto);
        let user = await this.userRepository.logInUser(userPassDto)
            .catch(reason => {throw(reason);});

        this.onLogin.emit(user);
        console.log(user);

        this.router.navigateByUrl('/main-login');
    }

    // Events
    public onLogin = new EventEmitter<UserDto>();


}

import {Router} from '@angular/router';
