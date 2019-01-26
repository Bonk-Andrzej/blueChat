import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ConversationService} from '../../services/conversation.service';
import {Observable} from 'rxjs';
import {MessageDto} from '../../repository/message/messageDto';
import {UserProfileService} from '../../services/user-profile.service';
import {MessageObs} from "../../services/model/messageObs";
import {UserObs} from '../../services/model/userObs';

@Component({
    selector: 'app-conversation',
    templateUrl: './conversation.component.html',
    styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit {

    conversationHeader: Observable<string>;
    messageContent: string;
    conversation: Observable<Array<MessageObs>>;

    @ViewChild('conversationListRef')
    private conversationListRef: ElementRef<HTMLDivElement>;

    constructor(private conversationService : ConversationService) {
    }

    ngOnInit() {

        this.conversationHeader = this.conversationService.getConversationHeaderObs();
        this.conversation = this.conversationService.getConversationObs();
        this.conversation.subscribe(() => {
            if (this.conversationListRef) {
                const nativeElement = this.conversationListRef.nativeElement;
                setTimeout(() => {
                    nativeElement.scrollTop = nativeElement.scrollHeight;
                }, 0)
            }
        })

    }

    public sendMessage(){
        this.conversationService.sendMessage(this.messageContent);
        this.cleanMessageInput();
    }

    private cleanMessageInput() {
        this.messageContent = '';
    }
}
