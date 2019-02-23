import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ColorService} from '../../services/background/color.service';
import {ColorObject} from '../../services/background/colorObject';

@Component({
  selector: 'app-background-wrapper',
  templateUrl: './background-wrapper.component.html',
  styleUrls: ['./background-wrapper.component.scss']
})
export class BackgroundWrapperComponent implements OnInit {

  currentColor : ColorObject;
  constructor(private colorService: ColorService) { }

  ngOnInit() {
    this.colorService.getCurrentColor().subscribe(color=>{
      this.currentColor = color;
    });
  }

}
