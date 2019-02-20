import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';
import {UserPassDto} from '../../repository/user/userPassDto';
import {SplashScreenService} from '../../services/splash-screen.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    colorButton: string;
    colorTextOnButton: string;

    user: UserPassDto = new UserPassDto();

    constructor(
        private colorService: ColorsService,
        private router: Router,
        private loginService: LoginService,
        private splashScreen: SplashScreenService) {
    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--yellow');
        this.colorTextOnButton = this.colorService.getColor('--black');
       this.splashScreen.hide()
    }

    public logIn() {

        this.loginService.Login(this.user).catch(()=> {
                alert('INVALID EMAIL OR PASS');
            }
        );

    }

    public openGithub() {
        window.open('https://github.com/newBlueChat/blueChat', '_blank');
    }

}
