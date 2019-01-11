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
            'content': 'Siemanko, co u Ciebie ?'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:13',
            'content': 'Pisze aplikacje czatu na androida'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:14',
            'content': 'A właściwie to męcze CSS i HTML :)'
        },
        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:15',
            'content': 'No nie ma lekko :P'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:16',
            'content': 'JAVA zdecydowanie przyjemniejsza'
        },
        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:17',
            'content': 'To napisz w REACT - Przemek by się cieszył ;)'
        },

        {
            'idSender': '3',
            'idInterlocutorName': '1',
            'date': '11:18',
            'content': 'No to na pewno, ale z dwojga złego wole Angluara, bo w Reactie jest straaaaaaszna JavaScript, której nie lubie. Po to wójek google wymyslił TS, żeby korzystać :)'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:19',
            'content': 'ale damy rade, troche trzena poprawcowac :)'
        },
        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:17',
            'content': 'Zobaczmy'
        },

        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:18',
            'content': 'Powodzenia :)'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:19',
            'content': 'Powodzenia :)'
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
