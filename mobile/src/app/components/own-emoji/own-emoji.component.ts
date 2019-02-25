import { Component, OnInit } from '@angular/core';
import {EmojiEvent} from '@ctrl/ngx-emoji-mart/ngx-emoji';
import {OwnEmojiServiceService} from '../../services/own-emoji-service.service';


@Component({
  selector: 'app-own-emoji',
  templateUrl: './own-emoji.component.html',
  styleUrls: ['./own-emoji.component.scss']
})
export class OwnEmojiComponent{


    emojiType : string;
    emojiQuality: number;

    constructor(private ownEmojiService: OwnEmojiServiceService) {
        this.emojiType = ownEmojiService.getType();
        this.emojiQuality = ownEmojiService.getQuality();
    }

    public handleClick($event: EmojiEvent) {
        this.ownEmojiService.pickEmoji($event.emoji);
        console.log($event.emoji.colons);
    }

}
