import {Component, Input, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';

@Component({
    selector: '[app-object-with-status]',
    templateUrl: './object-with-status.component.html',
    styleUrls: ['./object-with-status.component.scss']
})
export class ObjectWithStatusComponent implements OnInit {

    @Input() messageCounter: string;
    @Input() objectName: string;
    @Input() color: string;
    @Input() isActive: boolean;

    public statusColor;

    constructor(private colorsService: ColorsService) {}

    ngOnInit() {

       if(this.isActive){
           this.statusColor = this.colorsService.getColor('--green-dark');
       }else {
           this.statusColor = this.colorsService.getColor('--red-dark');
       }

        console.log('>>>>>>>>>>>>>>>>>>>>>>>> COLOR', this.statusColor);
    }

    randomColorTrigger() {
        // this.color = "#888888"
        // this.color = ('rgb(' + Math.floor(Math.random() * 255)
        //     + ',' + Math.floor(Math.random() * 255) + ','
        //     + Math.floor(Math.random() * 255) + ')');
    }
}
