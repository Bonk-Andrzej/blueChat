import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {BackgroundColorService} from '../../services/background/background-color.service';
import {ColorObject} from '../../services/background/colorObject';

@Component({
    selector: '[app-button-display]',
    templateUrl: './button-display.component.html',
    styleUrls: ['./button-display.component.scss']
})
export class ButtonDisplayComponent implements OnInit {


    @Input() title: string;
    @Input() state: string;
    currentColor: Observable<ColorObject>;

    constructor(private colorService: BackgroundColorService) {
    }

    ngOnInit() {
        this.currentColor = this.colorService.getCurrentColor();
    }

}
