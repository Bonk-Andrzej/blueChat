import {Component, OnInit} from '@angular/core';
import {ConversationService} from '../../services/conversation.service';
import {Observable} from 'rxjs';
import {MessageDto} from '../../repository/message/messageDto';
import {UserProfileService} from '../../services/user-profile.service';

@Component({
    selector: 'app-conversation',
    templateUrl: './conversation.component.html',
    styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit {

    messageOwnerName: string;
    interlocutorName: Observable<string>;
    idSender: number;
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
            'content': 'No to na pewno, ale z dwojga złego wole Angluara, bo w Reactie jest straaaaaaszna JavaScript, której nie lubie.'
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
            'date': '11:20',
            'content': 'Zobaczmy'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '1',
            'date': '11:20',
            'content': 'Zobaczmy'
        },

        {
            'idSender': '1',
            'idInterlocutorName': '3',
            'date': '11:21',
            'content': 'Powodzenia :)'
        },
        {
            'idSender': '3',
            'idInterlocutorName': '3',
            'date': '11:22',
            'content': 'Powodzenia :)'
        },

    ];

    conversation: Observable<Array<MessageDto>>;

    constructor(private conversationService : ConversationService,
                private userProfile: UserProfileService) {
    }

    ngOnInit() {
        this.idSender = this.userProfile.getUser().idUser;
        console.log(this.idSender, "my id")
        this.messageOwnerName = this.userProfile.getUser().nick;
        this.interlocutorName = this.conversationService.getInterlocutorName();
        this.conversation = this.conversationService.getConversation();

    }

}
