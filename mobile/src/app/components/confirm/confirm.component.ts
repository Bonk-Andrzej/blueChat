import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event} from '@angular/router';

@Component({
    selector: '[app-confirm]',
    templateUrl: './confirm.component.html',
    styleUrls: ['./confirm.component.scss']
})
export class ConfirmComponent implements OnInit {


    @Input() title;
    @Input() confirmButtonLabel;
    @Input() cancelButtonLabel;

    @Output() onCancel = new EventEmitter<Event>();
    @Output() onConfirm = new EventEmitter<Event>();

    constructor() {

    }

    ngOnInit() {
    }


    confirmButton(e : Event) {
        console.log('>>>> INNSIDE CONFIRM');
        this.onCancel.emit(e);
    }

    cancelButton(e : Event) {
        console.log('>>>> INNSIDE CANSEL');
        this.onConfirm.emit(e);
    }
}
