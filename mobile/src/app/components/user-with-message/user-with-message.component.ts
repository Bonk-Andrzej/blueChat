import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-user-with-message]',
    templateUrl: './user-with-message.component.html',
    styleUrls: ['./user-with-message.component.scss']
})
export class UserWithMessageComponent implements OnInit {

    @Input() userNick: string;
    @Input() message: string;
    randomColor: string;

    constructor() {

    }

    ngOnInit() {
        this.randomColorTrigger();
    }

    randomColorTrigger() {
        this.randomColor = "#cfcfcf"
        // this.randomColor = ('rgb(' + Math.floor(Math.random() * 255)
        //     + ',' + Math.floor(Math.random() * 255) + ','
        //     + Math.floor(Math.random() * 255) + ')');
    }
}
