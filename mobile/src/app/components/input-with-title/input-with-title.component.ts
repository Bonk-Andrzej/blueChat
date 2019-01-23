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

    constructor() {
    }

    ngOnInit() {
        this.value = this.value || "";
        this.titleInside = this.titleInside || "";
    }

    keyUpHandler() {
        this.valueChange.emit(this.value)
    }
}
