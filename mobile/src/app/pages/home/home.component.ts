import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


    form = {
        email: '',
        password: '',
        repeatPassword: ''
    };
    buttonColor: string;

    constructor(private colors: ColorsService) {
    }

    ngOnInit() {
        this.buttonColor = this.colors.getColor('--yellow');
    }

    showMenuHandler() {
        alert('showMenuHandler click');
    }

    onChange(e) {
        console.log(this.form);
    }
}
