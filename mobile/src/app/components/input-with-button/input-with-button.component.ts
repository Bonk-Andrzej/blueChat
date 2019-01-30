import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: '[app-input-with-button]',
    templateUrl: './input-with-button.component.html',
    styleUrls: ['./input-with-button.component.scss']
})
export class InputWithButtonComponent implements OnInit {

    @Input() title: string;
    @Input() buttonTitle: string;
    @Input() texArea: boolean;
    @Input() textAlign: string;

    @Input() value: string;
    @Output() valueChange = new EventEmitter<string>();
    @Output() onClickButton = new EventEmitter<null>();

    constructor() {
    }

    ngOnInit() {
        this.texArea = this.texArea || false;
        this.textAlign = this.textAlign || 'center';
    }

    throwValue() {
        this.valueChange.emit(this.value);
    }

    public clickButtonHandler() {
        this.onClickButton.emit();
    }


}
