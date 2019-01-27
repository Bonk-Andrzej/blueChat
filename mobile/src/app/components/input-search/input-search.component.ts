import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: '[app-input-search]',
    templateUrl: './input-search.component.html',
    styleUrls: ['./input-search.component.scss']
})
export class InputSearchComponent implements OnInit {

    @Input() value;
    @Output() valueChange = new EventEmitter<string>();


    constructor() {
    }

    ngOnInit() {
    }

    throwValue() {
        this.valueChange.emit(this.value);
    }
}
