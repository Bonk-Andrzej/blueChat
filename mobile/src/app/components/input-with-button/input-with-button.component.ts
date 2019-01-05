import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-input-with-button]',
  templateUrl: './input-with-button.component.html',
  styleUrls: ['./input-with-button.component.scss']
})
export class InputWithButtonComponent implements OnInit {

  @Input() title: 'jakis tytul';
  @Input() value: string;
  @Input() buttonTitle: string;
  constructor() { }

  ngOnInit() {
  }

}
