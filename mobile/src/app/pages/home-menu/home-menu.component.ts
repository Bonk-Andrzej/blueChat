import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
    selector: 'app-home-menu',
    templateUrl: './home-menu.component.html',
    styleUrls: ['./home-menu.component.scss']
})
export class HomeMenuComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit() {
    }

    logIn() {
        // alert('Log In');
        this.router.navigateByUrl('/home');
    }

    singUp() {
        // alert('Sing Up');
        this.router.navigateByUrl('/register');
    }

    aboutOus() {
        alert('About Ous');
        this.router.navigateByUrl('/about-ous');
    }

    contact() {
        alert('Contact -working at this..');
        this.router.navigateByUrl('/contact');
    }

    resetPass() {
        this.router.navigateByUrl('/reset-pass');
    }
}
