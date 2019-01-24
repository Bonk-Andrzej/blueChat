import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
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

    @ViewChild('conversationListRef')
    private conversationListRef: ElementRef<HTMLDivElement>;

    constructor(private conversationService : ConversationService,
                private userProfile: UserProfileService) {
    }

    ngOnInit() {

        this.idSender = this.userProfile.getUser().getIdUser()
        this.messageOwnerName = this.userProfile.getUser().getNick()
        this.interlocutorName = this.conversationService.getInterlocutorName();
        this.conversation = this.conversationService.getConversation();
        this.conversation.subscribe(() => {

            if (this.conversationListRef) {

                const nativeElement = this.conversationListRef.nativeElement;
                setTimeout(() => {
                    console.log(nativeElement.scrollHeight)
                    nativeElement.scrollTop = nativeElement.scrollHeight;
                }, 0)
            }
        })

    }

    public sendMessage(){
        let messageDto = new MessageDto();
        messageDto.content = this.messageContent;
        messageDto.senderId = this.userProfile.getUser().getIdUser()
        messageDto.receiverId = this.conversationService.getInterlocutorId();
        this.conversationService.sendMessage(messageDto);

        this.messageContent = "";
    }

}
