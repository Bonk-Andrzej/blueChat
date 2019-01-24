import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-box-profiles]',
    templateUrl: './box-profiles.component.html',
    styleUrls: ['./box-profiles.component.scss']
})
export class BoxProfilesComponent implements OnInit {

    @Input() title: string;
    @Input() content: string;
    @Input() color: string;

    constructor() {
    }

    ngOnInit() {
    }

}
