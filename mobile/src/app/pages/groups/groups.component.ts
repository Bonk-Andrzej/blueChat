import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-groups',
    templateUrl: './groups.component.html',
    styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit {

    groups = [
        {
            'name': 'Java Poz 9',
        },
        {
            'name': 'random',
        },
        {
            'name': 'off topic' ,
        }
    ];

    constructor() {
    }

    ngOnInit() {

    }

}

