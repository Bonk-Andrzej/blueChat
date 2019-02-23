import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Observable} from 'rxjs';
import {BackgroundColorService} from '../../services/background/background-color.service';
import {ColorObject} from '../../services/background/colorObject';
import {ColorsService} from '../../services/colors.service';

@Component({
    selector: '[app-button-wide-on-off]',
    templateUrl: './button-wide-on-off.component.html',
    styleUrls: ['./button-wide-on-off.component.scss']
})
export class ButtonWideOnOffComponent implements OnInit {

    @Input() title: string;
    @Input() isActive: boolean;
    @Output() isActiveChange = new EventEmitter<boolean>();

    onButtonColor: ColorObject;
    offButtonColor: ColorObject;

    private whiteColor: ColorObject;

    constructor(private bgColorService: BackgroundColorService,
                private colorService: ColorsService) {

        this.whiteColor = new ColorObject("",this.colorService.getColor("--white"));
        this.onButtonColor = this.whiteColor;
        this.offButtonColor = this.whiteColor;
    }

    ngOnInit() {
        this.bgColorService.getCurrentColor().subscribe(color => {
            this.setButtons(color);
        })
    }

    setButtons(color : ColorObject) {
        if (this.isActive) {
            this.onButtonColor = color
        } else {
            this.offButtonColor = color
        }
    }

    toggleButton() {
        this.isActive = !this.isActive;
        if (this.isActive) {
            this.onButtonColor = this.offButtonColor
            this.offButtonColor = this.whiteColor;
        } else {
            this.offButtonColor = this.onButtonColor
            this.onButtonColor = this.whiteColor;
        }
        this.isActiveChange.emit(this.isActive);
    }
}
