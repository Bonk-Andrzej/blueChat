import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {


    form = {
        email: '',
        password: '',
        repeatPassword: ''
    };
    buttonColor: string;
    colorTextOnButton: string;

    constructor(private colorService: ColorsService, private router: Router) {
    }

    ngOnInit() {
        this.buttonColor = this.colorService.getColor('--yellow');
        this.colorTextOnButton = this.colorService.getColor('--black');
    }

    showMenuHandler() {
        this.router.navigateByUrl('/home-menu');
    }

    onChange(e) {
        console.log(this.form);
    }
}
