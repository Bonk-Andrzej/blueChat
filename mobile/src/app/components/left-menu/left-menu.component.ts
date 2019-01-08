import { Component, OnInit } from '@angular/core';
import {LeftMenuServiceService} from '../../services/left-menu-service.service';

@Component({
  selector: 'app-left-menu',
  templateUrl: './left-menu.component.html',
  styleUrls: ['./left-menu.component.scss']
})
export class LeftMenuComponent implements OnInit {


  menuDisplay = "none"

  constructor(public leftMenuService: LeftMenuServiceService) { }

  ngOnInit() {
    this.leftMenuService.onToggle(this.onToggleHandler.bind(this))
  }
  private onToggleHandler(isDisplay: boolean){

    if(isDisplay){
      this.menuDisplay = "block";
    }else{
      this.menuDisplay = "none";
    }

  }

}
