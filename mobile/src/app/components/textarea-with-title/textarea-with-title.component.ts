import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-textarea-with-title]',
    templateUrl: './textarea-with-title.component.html',
    styleUrls: ['./textarea-with-title.component.scss']
})
export class TextareaWithTitleComponent implements OnInit {

    @Input() title: string;
    @Input() textInside: string;

    constructor() {
    }

    ngOnInit() {
    }

}
