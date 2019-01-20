import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-object-with-status]',
    templateUrl: './object-with-status.component.html',
    styleUrls: ['./object-with-status.component.scss']
})
export class ObjectWithStatusComponent implements OnInit {

    @Input() messageCounter: string;
    @Input() objectName: string;
    @Input() color: string;

    constructor() {

    }

    ngOnInit() {
        console.log('>>>>>>>>>>>>>>>>>>>>>>>> COLOR', this.color);
        // this.randomColorTrigger();
    }

    randomColorTrigger() {
        // this.color = "#888888"
        // this.color = ('rgb(' + Math.floor(Math.random() * 255)
        //     + ',' + Math.floor(Math.random() * 255) + ','
        //     + Math.floor(Math.random() * 255) + ')');
    }
}
