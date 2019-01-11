import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-object-with-checkbox]',
  templateUrl: './object-with-checkbox.component.html',
  styleUrls: ['./object-with-checkbox.component.scss']
})
export class ObjectWithCheckboxComponent implements OnInit {


    @Input() objectName: string;
    randomColor: string;

    constructor() {

    }

    ngOnInit() {
        this.randomColorTrigger();
    }

    randomColorTrigger() {

        this.randomColor = ('rgb(' + Math.floor(Math.random() * 255)
            + ',' + Math.floor(Math.random() * 255) + ','
            + Math.floor(Math.random() * 255) + ')');
    }


}