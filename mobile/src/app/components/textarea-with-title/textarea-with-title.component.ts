import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: '[app-textarea-with-title]',
    templateUrl: './textarea-with-title.component.html',
    styleUrls: ['./textarea-with-title.component.scss']
})
export class TextareaWithTitleComponent implements OnInit {

    @Input() title: string;
    @Input() textareaValue: string;
    @Output()textareaValueChange = new EventEmitter<string>();

    constructor() {
    }

    ngOnInit() {
        this.textareaValue = this.textareaValue || "";
    }

    public onKeyUp(){
        this.textareaValueChange.emit(this.textareaValue);
    }

}
