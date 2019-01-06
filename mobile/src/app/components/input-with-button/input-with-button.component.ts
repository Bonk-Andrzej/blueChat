import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-input-with-button]',
    templateUrl: './input-with-button.component.html',
    styleUrls: ['./input-with-button.component.scss']
})
export class InputWithButtonComponent implements OnInit {

    @Input() title: string;
    @Input() value: string;
    @Input() buttonTitle: string;

    textAreaStyleTextAlign = "center"
    constructor() {
    }

    ngOnInit() {
    }

    keyPressHandler(e) {
        console.log("press")
        if(e.target.value.length > 0){
            this.textAreaStyleTextAlign = "left"
        }else {
            this.textAreaStyleTextAlign = "center"
        }
    }


}
