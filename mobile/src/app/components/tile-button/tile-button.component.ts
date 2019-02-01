import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-tile-button]',
  templateUrl: './tile-button.component.html',
  styleUrls: ['./tile-button.component.scss']
})
export class TileButtonComponent implements OnInit {

  @Input() iconNameFromAssets: string;
  @Input() buttonName:string;

  public iconStyle = {
    backgroundImage: 'none'
  }

  constructor() { }

  ngOnInit() {
    this.buttonName = this.buttonName || "";

    if(this.iconNameFromAssets){
      this.iconStyle.backgroundImage = `url(assets/${this.iconNameFromAssets})`
    }
  }

}
