import {Component, Input, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';
import {BehaviorSubject, Observable} from "rxjs";

@Component({
    selector: '[app-object-with-status]',
    templateUrl: './object-with-status.component.html',
    styleUrls: ['./object-with-status.component.scss']
})
export class ObjectWithStatusComponent implements OnInit {

    @Input() messageCounter: string;
    @Input() objectName: string;
    @Input() color: string;
    @Input() isActive: Observable<boolean>;

    public statusColor;

    constructor(private colorsService: ColorsService) {
    }

    ngOnInit() {

        if (this.isActive != null) {

            this.isActive.subscribe(value => {
                if (value) {
                    this.statusColor = this.colorsService.getColor('--green-dark');
                } else {
                    this.statusColor = this.colorsService.getColor('--red-dark');
                }
            })
        }

    }
}
