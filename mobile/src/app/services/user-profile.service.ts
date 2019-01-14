import {Injectable} from '@angular/core';
import {UserRepositoryService} from '../repository/user/user-repository.service';
import {LoginService} from './login.service';
import {UserDto} from '../repository/user/userDto';

@Injectable({
    providedIn: 'root'
})
export class UserProfileService {

    public userDto: UserDto;

    constructor(private userRepository: UserRepositoryService,
                private loginService: LoginService) {
        this.loginService.onLogin.subscribe((user) => {
            this.userDto = user;
        });
        console.log('UserProfileService -- subscribe');
    }

    ngOnInit() {
        // this.loginService.onLogin.subscribe((user) => {
        //     this.userDto = user;
        //     console.log(user)
        // });
        // alert("UserProfileService -- init")

    }

    public getUser(): UserDto {
        return this.userDto;
    }

}
