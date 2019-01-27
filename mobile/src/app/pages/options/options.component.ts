import {Component, OnInit} from '@angular/core';
import {ColorObject} from '../../services/background/colorObject';
import {ColorService} from '../../services/background/color.service';

@Component({
    selector: 'app-options',
    templateUrl: './options.component.html',
    styleUrls: ['./options.component.scss']
})
export class OptionsComponent implements OnInit {

    public colorsList: Array<ColorObject>;

    constructor(private bgColorService: ColorService) {
    }

    ngOnInit() {
        this.colorsList = this.bgColorService.colors;
    }

    setBackground(color: ColorObject){
        this.bgColorService.setCurrentColor(color);
    }

}
