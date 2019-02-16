import {Component, Input, OnInit} from '@angular/core';
import {ButtonState} from '../../pages/user-profile/user-profile.component';
import {Observable} from 'rxjs';

@Component({
  selector: '[app-tile-button]',
  templateUrl: './tile-button.component.html',
  styleUrls: ['./tile-button.component.scss']
})
export class TileButtonComponent implements OnInit {

  @Input() iconNameFromAssets: string;
  @Input() buttonName:string;
  @Input() buttonsState: Observable<ButtonState>;

  public iconStyle = {
    backgroundImage: 'none'
  }

  constructor() { }

  ngOnInit() {
    this.buttonName = this.buttonName || "";
    this.buttonsState = this.buttonsState || new Observable();

    if(this.iconNameFromAssets){
      this.iconStyle.backgroundImage = `url(assets/${this.iconNameFromAssets})`
    }

    this.buttonsState.subscribe(state => {
       this.buttonName = state.name
        this.iconStyle.backgroundImage = `url(assets/${state.iconName})`
    })
  }

}
