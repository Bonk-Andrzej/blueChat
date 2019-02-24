import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-title]',
    templateUrl: './title.component.html',
    styleUrls: ['./title.component.scss']
})
export class TitleComponent implements OnInit {

    @Input() valueContent: string;
    @Input() textAlign: "center" | "left" | "right";
    @Input() fontSize: string;

    titleStyle = {}

    constructor() {
    }

    ngOnInit() {

        this.titleStyle = {
            textAlign : this.textAlign || "center" ,
            fontSize : this.fontSize || '1.5em'
        }

    }

}
