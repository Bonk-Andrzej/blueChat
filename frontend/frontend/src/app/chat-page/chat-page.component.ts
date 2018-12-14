import {Component, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-chat-page',
    templateUrl: './chat-page.component.html',
    styleUrls: ['./chat-page.component.scss']
})
export class ChatPageComponent implements OnInit {
    @Output() activeUserStatusBar2 = {
        'backgroundColor': '#df1b37'
    };
    ngOnInit(): void {
    }

}


