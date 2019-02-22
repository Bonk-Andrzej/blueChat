import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Observable} from 'rxjs';
import {ColorService} from '../../services/background/color.service';

@Component({
    selector: '[app-button-wide-on-off]',
    templateUrl: './button-wide-on-off.component.html',
    styleUrls: ['./button-wide-on-off.component.scss']
})
export class ButtonWideOnOffComponent implements OnInit {

    @Input() title: string;
    @Input() isActive: boolean;
    @Output() isActiveChange = new EventEmitter<boolean>();
    onButtonColor: Observable<string>;
    offButtonColor: Observable<string>;
    currentColor: Observable<string>;

    constructor(private colorService: ColorService) {

    }

    ngOnInit() {
        this.currentColor = this.colorService.getCurrentColor();
        this.setButtons();
    }

    setButtons() {
        if (this.isActive) {
            this.onButtonColor = this.currentColor;
        } else {
            this.offButtonColor = this.currentColor;
        }
    }

    toggleButton() {
        this.isActive = !this.isActive;
        if (this.isActive) {
            this.onButtonColor = this.currentColor;
            this.offButtonColor = null;
        } else {
            this.onButtonColor = null;
            this.offButtonColor = this.currentColor;
        }
        this.isActiveChange.emit(this.isActive);
    }
}
