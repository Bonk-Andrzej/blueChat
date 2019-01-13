import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: '[app-input-with-title]',
    templateUrl: './input-with-title.component.html',
    styleUrls: ['./input-with-title.component.scss']
})
export class InputWithTitleComponent implements OnInit {

    @Input() title: string;
    @Input() type: string;
    @Input() value: string;
    @Input() titleInside: string;
    @Output() valueChange = new EventEmitter<string>();
    @Output() inputChange = new EventEmitter<Event>();

    constructor() {
    }

    ngOnInit() {
    }

    onChangeHandler(event: Event) {
        this.valueChange.emit(this.value)
        this.inputChange.emit(event)
    }
}
