import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {NewUser} from '../repository/user-repository/newUser';
import {UserRepositoryService} from '../repository/user-repository/user-repository.service';
import {AuthorizationServiceService} from '../services/authorization-service/authorization-service.service';

import {Router} from '@angular/router';
import {WSRClientService} from '../services/WSRClient/wsrclient.service';
import {AuthSessionDTO} from "../services/WSRClient/dto/AuthSessionDTO";
import {RemoteType} from "../services/WSRClient/types/RemoteType";

@Component({
    selector: 'app-login-page',
    templateUrl: './login-page.component.html',
    styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {


    @ViewChild("status")
    private status: ElementRef<HTMLInputElement>;
    public access = '';
    private loggedIdUser: number;
    public statusBar = {
        'backgroundColor': '#56c130'
    };

    public loginButtonsDisabled: boolean = true;
    public loginButton = {
        'backgroundColor': '#333',
        'cursor': 'wait'
}


    ngOnInit() {
    }

    constructor(private http: HttpClient,
                private userRepository: UserRepositoryService,
                private authorizationService: AuthorizationServiceService,
                private router: Router,
                private wsr: WSRClientService) {

        wsr.WRSClient.onOpen().subscribe(()=>{
            this.loginButton.backgroundColor = "#56c130";
            this.loginButton.cursor = "pointer";
            this.loginButtonsDisabled = false;
        })

    }


    public registerUser() {
        const login: HTMLInputElement = document.querySelector('#login');
        const pass: HTMLInputElement = document.querySelector('#password');

        const newUser: NewUser = new NewUser(login.value, pass.value);
        console.log(newUser.nick);
        console.log(newUser.password);

        const objectObservable = this.userRepository.postNewUser(newUser);
        objectObservable.subscribe(msg => {
            console.log(msg);
        });
    }

    public loginUser() {

        const login: HTMLInputElement = document.querySelector('#login');
        const pass: HTMLInputElement = document.querySelector('#password');

        this.authorizationService.authenticate(login.value, pass.value);

        this.authorizationService.onAuthorizated(() => {
            this.router.navigateByUrl('/chat');


            const authSessionDTO = new AuthSessionDTO();

                authSessionDTO.setUserId(this.authorizationService.getAuthorizatedUser().idUser)
                    this.wsr.WRSClient.executeRemoteProcedure(RemoteType.AUTHSESSION, authSessionDTO);


        });
        this.authorizationService.onFaild(() => {
                this.statusBar.backgroundColor = '#df1b37';
                this.status.nativeElement.innerHTML = "incorrect login or password";
            }
        );
    }


    public getLoggedIdUser() {
        return this.loggedIdUser;
    }

}




