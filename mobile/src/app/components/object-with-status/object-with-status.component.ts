import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-object-with-status]',
    templateUrl: './object-with-status.component.html',
    styleUrls: ['./object-with-status.component.scss']
})
export class ObjectWithStatusComponent implements OnInit {

    @Input() messageCounter: string;
    @Input() objectName: string;
    randomColor: string;

    constructor() {

    }

    ngOnInit() {
        this.randomColorTrigger();
    }

    randomColorTrigger() {
        this.randomColor = "#888888"
        // this.randomColor = ('rgb(' + Math.floor(Math.random() * 255)
        //     + ',' + Math.floor(Math.random() * 255) + ','
        //     + Math.floor(Math.random() * 255) + ')');
    }
}
