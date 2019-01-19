import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: '[app-list-users-with-title]',
    templateUrl: './list-users-with-title.component.html',
    styleUrls: ['./list-users-with-title.component.scss']
})
export class ListUsersWithTitleComponent implements OnInit {

    @Input() title: string;
    @Input() backgroundColor: string;
    @Input() titleStyle: object;
    @Input() listStyle: object;
    constructor() {
    }

    ngOnInit() {
        this.titleStyle = this.titleStyle || {};
        this.listStyle = this.listStyle || {};
    }

}
