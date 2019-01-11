import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-input-with-inside-text]',
    templateUrl: './input-with-inside-text.component.html',
    styleUrls: ['./input-with-inside-text.component.scss']
})
export class InputWithInsideTextComponent implements OnInit {

    @Input() type: string;
    @Input() value: string;
    @Input() titleInside: string;

    constructor() {
    }

    ngOnInit() {
    }

    onChangeHandler(event: Event) {
        // this.valueChange.emit(this.value)
        // this.inputChange.emit(event)
        // console.log(this.value)
    }

}
