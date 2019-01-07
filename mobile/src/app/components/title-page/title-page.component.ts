import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-title-page]',
  templateUrl: './title-page.component.html',
  styleUrls: ['./title-page.component.scss']
})
export class TitlePageComponent implements OnInit {

  @Input() valueContent: string;
  constructor() { }

  ngOnInit() {
  }

}
