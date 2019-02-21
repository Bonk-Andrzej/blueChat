import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-button-display]',
  templateUrl: './button-display.component.html',
  styleUrls: ['./button-display.component.scss']
})
export class ButtonDisplayComponent implements OnInit {


  @Input() title : string;
  @Input() state: string;
  constructor() { }

  ngOnInit() {
  }

}
