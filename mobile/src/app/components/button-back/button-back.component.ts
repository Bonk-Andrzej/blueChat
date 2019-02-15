import {Component, Input, OnInit} from '@angular/core';
import { Location } from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: '[app-button-back]',
  templateUrl: './button-back.component.html',
  styleUrls: ['./button-back.component.scss']
})
export class ButtonBackComponent implements OnInit {

  constructor(private location : Location,
              private router: Router) { }

  @Input() navigateToUrl: string;

  ngOnInit() {
  }

  public previousPage(){
    if(this.navigateToUrl != null){
      this.router.navigateByUrl(this.navigateToUrl)
    }else {
        this.location.back();
        this.router.navigate([])
    }
  }

}
