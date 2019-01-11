import {Component, OnInit} from '@angular/core';
import {ColorsService} from '../../services/colors.service';

@Component({
    selector: 'app-create-group',
    templateUrl: './create-group.component.html',
    styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {

    groupName: string;
    colorButton: string;
    colorTextOnButton: string;
    users = [
        {
            'name': 'Adam Kowalski',
        },
        {
            'name': 'Paweł Jastrzębski',
        },
        {
            'name': 'Marian Kowalski'
        },
        {
            'name': 'Marek Nowak'
        },
        {
            'name': 'Łukasz Paprotka'
        },
        {
            'name': 'Tomek Malinowski'
        },
        {
            'name': 'Paweł Janas'
        },
        {
            'name': 'Marek Nowak'
        },
        {
            'name': 'Łukasz Paprotka'
        },
        {
            'name': 'Tomek Malinowski'
        },
        {
            'name': 'Paweł Janas'
        }
    ];

    constructor(private colorService: ColorsService) {


    }

    ngOnInit() {
        this.colorButton = this.colorService.getColor('--orange');
        this.colorTextOnButton = this.colorService.getColor('--white-light');
    }

}
