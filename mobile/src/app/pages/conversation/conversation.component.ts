import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ConversationService} from '../../services/conversation.service';
import {Observable} from 'rxjs';
import {MessageObs} from '../../services/model/messageObs';
import {OwnEmojiServiceService} from '../../services/own-emoji-service.service';
import {EmojiPipePipe} from '../../pipes/emoji-pipe.pipe';

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

    constructor(private conversationService: ConversationService,
                private ownEmojiService: OwnEmojiServiceService,
                public  ownEmojiPipe: EmojiPipePipe) {
        this.messageContent = '';
    }

    ngOnInit() {

        this.conversationHeader = this.conversationService.getConversationHeaderObs();
        this.conversation = this.conversationService.getConversationObs();
        this.conversation.subscribe(() => {
            if (this.conversationListRef) {
                const nativeElement = this.conversationListRef.nativeElement;
                setTimeout(() => {
                    nativeElement.scrollTop = nativeElement.scrollHeight;
                }, 0);
            }
        });
        this.ownEmojiService.onSelectetEmoji.subscribe(emojiString => {
            this.messageContent = this.messageContent + emojiString;
        });

    }

    public sendMessage() {
        this.conversationService.sendMessage(this.messageContent);
        this.cleanMessageInput();
    }

    private cleanMessageInput() {
        this.messageContent = '';
    }
}
