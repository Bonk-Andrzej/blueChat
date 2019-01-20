import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: '[app-button-sending]',
    templateUrl: './button-sending.component.html',
    styleUrls: ['./button-sending.component.scss']
})
export class ButtonSendingComponent implements OnInit {

    @Input() value: string;
    @Output() valueChange = new EventEmitter<string>();
    @Output() onButtonClick = new EventEmitter<Event>();

    constructor() {
    }

    ngOnInit() {
    }

    buttonClickHandler(e: Event){
        this.onButtonClick.emit(e)
    }

    public textUpdate(){
        this.valueChange.emit(this.value);
    }

}
