import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-conversation',
    templateUrl: './conversation.component.html',
    styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit {

    messageOwnerName: string;
    interlocutorName: string;
    idSender: string;
    messagesDTOs = [
        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:12',
            'content': 'Text from sender'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:13',
            'content': 'Response interlocutor'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:14',
            'content': 'Response interlocutor'
        },
        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:15',
            'content': 'Response interlocutor'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:16',
            'content': 'Response interlocutor'
        },
        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:17',
            'content': 'Response interlocutor'
        },

        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:18',
            'content': 'Response interlocutor'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:19',
            'content': 'Response interlocutor'
        },

    ];

    constructor() {
        this.idSender = '1';
    }

    ngOnInit() {
        this.messageOwnerName = 'Paweł Jastrzębski';
        this.interlocutorName = 'Igor Sowiński';
    }

}
