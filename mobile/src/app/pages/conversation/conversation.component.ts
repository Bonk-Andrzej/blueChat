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
    messageContent: string;
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

    public sendMessage(){
        let messageDto = new MessageDto();
        messageDto.content = this.messageContent;
        messageDto.senderId = this.userProfile.getUser().idUser;
        messageDto.receiverId = this.conversationService.getInterlocutorId();
        this.conversationService.sendMessage(messageDto);

        this.messageContent = "";
    }

}
