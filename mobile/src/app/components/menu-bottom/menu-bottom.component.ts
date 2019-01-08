import { Component, OnInit } from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';

@Component({
  selector: '[app-menu-bottom]',
  templateUrl: './menu-bottom.component.html',
  styleUrls: ['./menu-bottom.component.scss']
})
export class MenuBottomComponent implements OnInit {

  constructor( public leftMenuService : LeftMenuServiceService) { }

  ngOnInit() {
  }

}
