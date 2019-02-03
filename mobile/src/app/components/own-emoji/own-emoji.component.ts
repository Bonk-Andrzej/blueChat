import { Component, OnInit } from '@angular/core';
import {EmojiEvent} from '@ctrl/ngx-emoji-mart/ngx-emoji';
import {OwnEmojiServiceService} from '../../services/own-emoji-service.service';


@Component({
  selector: 'app-own-emoji',
  templateUrl: './own-emoji.component.html',
  styleUrls: ['./own-emoji.component.scss']
})
export class OwnEmojiComponent{

    themes = [
        'native',
        'apple',
        'google',
        'twitter',
        'emojione',
        'messenger',
        'facebook',
    ];
    set = 'google';
    native = true;

    setTheme(set: string) {
        this.native = set === 'google';
        this.set = set;
    }

    constructor(private ownEmojiService: OwnEmojiServiceService) { }

    public handleClick($event: EmojiEvent) {
        this.ownEmojiService.pickEmoji($event.emoji);
        console.log($event.emoji.colons);
    }

}
