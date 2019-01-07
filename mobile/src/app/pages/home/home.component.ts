import { Component, OnInit } from '@angular/core';
import {ColorsService} from '../../services/colors.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private colorService: ColorsService) { }

  ngOnInit() {
    this.colorService.getColor('--yellow')
  }

}
