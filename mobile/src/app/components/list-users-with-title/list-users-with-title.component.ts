import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: '[app-list-users-with-title]',
  templateUrl: './list-users-with-title.component.html',
  styleUrls: ['./list-users-with-title.component.scss']
})
export class ListUsersWithTitleComponent implements OnInit {

  @Input() title: string;

  constructor() { }

  ngOnInit() {
  }

}
