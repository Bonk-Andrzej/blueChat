import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit {

  receiverName: string;
  constructor() { }

  ngOnInit() {
    this.receiverName = 'Igor Sowiński';
  }

}
