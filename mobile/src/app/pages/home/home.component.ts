import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';
import {UserPassDto} from '../../repository/user/userPassDto';

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
        private loginService: LoginService) {
    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--yellow');
        this.colorTextOnButton = this.colorService.getColor('--black');
    }

    public logIn() {


        try {
            this.loginService.Login(this.user);
            this.router.navigateByUrl('/main-login');

        } catch (e) {
            alert('INVALID PASS');
        }
    }

    public openGithub(){
        window.open('https://github.com/newBlueChat/blueChat', '_blank');
    }

}
