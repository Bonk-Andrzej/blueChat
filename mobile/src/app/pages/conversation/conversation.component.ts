import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ConversationService} from '../../services/conversation.service';
import {Observable} from 'rxjs';
import {MessageObs} from '../../services/model/messageObs';
import {OwnEmojiServiceService} from '../../services/own-emoji-service.service';
import {EmojiPipePipe} from '../../pipes/emoji-pipe.pipe';
import {SplashScreenService} from '../../services/splash-screen.service';

@Component({
    selector: 'app-conversation',
    templateUrl: './conversation.component.html',
    styleUrls: ['./conversation.component.scss']
})
export class ConversationComponent implements OnInit, OnDestroy {

    conversationHeader: Observable<string>;
    messageContent: string;
    conversation: Observable<Array<MessageObs>>;

    isDisplayEmoji: boolean;
    @ViewChild('conversationListRef')
    private conversationListRef: ElementRef<HTMLDivElement>;

    constructor(private conversationService: ConversationService,
                private ownEmojiService: OwnEmojiServiceService,
                private splashScreen: SplashScreenService) {
        this.messageContent = '';
        this.isDisplayEmoji = false;
    }

    ngOnInit() {

        this.splashScreen.hide();
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
            // this.messageContent = this.ownEmojiPipe.transform(this.messageContent + emojiString,null);
        });

    }

    public sendMessage() {
        this.conversationService.sendMessage(this.messageContent);
        this.cleanMessageInput();
    }

    private cleanMessageInput() {
        this.messageContent = '';
    }

    toggleEmojiPicker() {
        this.isDisplayEmoji = !this.isDisplayEmoji;
    }

    ngOnDestroy(): void {
        this.conversationService.endConversation();
    }
}
