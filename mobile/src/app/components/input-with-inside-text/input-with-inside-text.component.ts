import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: '[app-input-with-inside-text]',
    templateUrl: './input-with-inside-text.component.html',
    styleUrls: ['./input-with-inside-text.component.scss']
})
export class InputWithInsideTextComponent implements OnInit {

    @Input() type: string;
    @Input() value: string;
    @Input() titleInside: string;
    @Output() valueChange = new EventEmitter<string>();

    constructor() {
    }

    ngOnInit() {
    }

    throwValue() {
        this.valueChange.emit(this.value)
        console.log('>>>>> INPUT WITH EMAIL' , this.value)
    }

}
