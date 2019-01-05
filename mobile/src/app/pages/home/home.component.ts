import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


    form = {
        email: "",
        password: "",
        repeatPassword: ""
    }

    constructor() {
    }

    ngOnInit() {
    }

    showMenuHandler() {
        alert("showMenuHandler click")
    }
    onChange(e){
        console.log(this.form)
    }
}
