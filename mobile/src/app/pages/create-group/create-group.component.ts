import { Component, OnInit } from '@angular/core';
import {ColorsService} from '../../services/colors.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {

  colorButton: string;
  colorTextOnButton: string;
  constructor(private colorService: ColorsService) { }

  ngOnInit() {
    this.colorButton = this.colorService.getColor('--orange');
    this.colorTextOnButton = this.colorService.getColor('--white-light');
  }

}
