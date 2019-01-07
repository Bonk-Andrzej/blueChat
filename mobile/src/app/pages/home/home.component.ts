import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    colorButton : string;

    constructor(private colorService: ColorsService, private router: Router) {
    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--yellow');
    }

    hamMenu() {
        this.router.navigateByUrl('/home-menu');
    }

}
