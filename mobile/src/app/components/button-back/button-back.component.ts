import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: '[app-button-back]',
  templateUrl: './button-back.component.html',
  styleUrls: ['./button-back.component.scss']
})
export class ButtonBackComponent implements OnInit {

  constructor(private location : Location) { }

  ngOnInit() {
  }

  public previousPage(){
    this.location.back();
  }

}
