import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-button-display-option]',
  templateUrl: './button-display-option.component.html',
  styleUrls: ['./button-display-option.component.scss']
})
export class ButtonDisplayOptionComponent implements OnInit {

  @Input() title: string;
  @Input() colorObject: string;
  @Input() content: string;

  constructor() { }

  ngOnInit() {
  }

}
