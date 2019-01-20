import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-object-with-x]',
    templateUrl: './object-with-x.component.html',
    styleUrls: ['./object-with-x.component.scss']
})
export class ObjectWithXComponent implements OnInit {


    @Input() objectName: string;
    @Input() statusColor: string;

    constructor() {

    }

    ngOnInit() {
        // this.randomColorTrigger();
    }

    randomColorTrigger() {

        // this.randomColor = ('rgb(' + Math.floor(Math.random() * 255)
        //     + ',' + Math.floor(Math.random() * 255) + ','
        //     + Math.floor(Math.random() * 255) + ')');
    }


}
