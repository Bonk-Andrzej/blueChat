import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ColorService} from '../../services/background/color.service';

@Component({
  selector: 'app-background-wrapper',
  templateUrl: './background-wrapper.component.html',
  styleUrls: ['./background-wrapper.component.scss']
})
export class BackgroundWrapperComponent implements OnInit {

  currentColor : Observable<string>;
  constructor(private colorService: ColorService) { }

  ngOnInit() {
    this.currentColor = this.colorService.getCurrentColor();
  }

}
