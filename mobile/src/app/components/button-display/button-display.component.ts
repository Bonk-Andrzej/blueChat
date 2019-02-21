import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ColorService} from '../../services/background/color.service';

@Component({
    selector: '[app-button-display]',
    templateUrl: './button-display.component.html',
    styleUrls: ['./button-display.component.scss']
})
export class ButtonDisplayComponent implements OnInit {


    @Input() title: string;
    @Input() state: string;
    currentColor: Observable<string>;

    constructor(private colorService: ColorService) {
    }

    ngOnInit() {
        this.currentColor = this.colorService.getCurrentColor();
    }

}
