import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event} from "@angular/router";

@Component({
    selector: '[confirm-box]',
    templateUrl: './confirm-box.component.html',
    styleUrls: ['./confirm-box.component.scss']
})
export class ConfirmBoxComponent implements OnInit {

    @Input() title;
    @Input() confirmButtonLabel;
    @Input() cancelButtonLabel;

    @Output() onCancel = new EventEmitter<Event>();
    @Output() onConfirm = new EventEmitter<Event>();

    constructor() {
    }

    ngOnInit() {
        this.title = this.title || "Do you Want To Log Out?";
        this.confirmButtonLabel = this.confirmButtonLabel || "Ok";
        this.cancelButtonLabel = this.cancelButtonLabel || "Cancel";

    }

    cancelClickHandler(e){
        this.onCancel.emit(e);
    }

    confirmClickHandler(e){
        this.onConfirm.emit(e);
    }
}
